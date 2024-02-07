package com.ricky.jetpet.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.data.network.token.AccessTokenRepository
import com.ricky.jetpet.domain.use_case.GetAnimals
import com.ricky.jetpet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreUtil: DataStoreUtil,
    private val getAnimals: GetAnimals,
    private val tokenRepository: AccessTokenRepository,
) : ViewModel() {

    private var _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    companion object {
        const val TAG = "infoteste"
    }

    init {
        viewModelScope.launch {
            tokenRepository.fetchAccessToken()?.let { accessToken ->
                getAnimals("Bearer ${accessToken.accessToken}")
            }
            getTheme()
        }
    }

    private fun getAnimals(token: String) {
        getAnimals(_state.value.page, token).onEach { result ->
            when (result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            error = result.message.toString(),
                            isLoading = true
                        )
                    }
                }

                is Resource.Loading -> {
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                }

                is Resource.Success -> {
                    val pets = _state.value.pets.toMutableList()
                    pets.addAll(result.data ?: emptyList())

                    _state.update {
                        it.copy(
                            error = "",
                            isLoading = true,
                            pets = pets.toList()
                        )
                    }
                }
            }
        }.launchIn(viewModelScope)
    }

    private suspend fun getTheme() {
        dataStoreUtil.getTheme().collect { isDark ->
            _state.update {
                it.copy(
                    isDark = isDark
                )
            }
        }
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnSwitch -> {
                viewModelScope.launch {
                    dataStoreUtil.saveTheme(isDark = !_state.value.isDark)
                }
            }

            HomeEvent.LoadPets -> {
                viewModelScope.launch {
                    tokenRepository.fetchAccessToken()?.let {
                        Log.i("infoteste", "onEvent: $it")
                        getAnimals("Bearer ${it.accessToken}")
                    }
                }
                _state.update {
                    it.copy(
                        page = _state.value.page + 1
                    )
                }
            }
        }
    }
}