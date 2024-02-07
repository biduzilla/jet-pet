package com.ricky.jetpet.presentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.domain.use_case.GetAnimal
import com.ricky.jetpet.utils.Constants
import com.ricky.jetpet.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val saveStateHandle: SavedStateHandle,
    private val getAnimal: GetAnimal,
    private val dataStoreUtil: DataStoreUtil
) :
    ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()
    lateinit var token: String

    init {
        viewModelScope.launch {
            saveStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
                dataStoreUtil.getToken().collect {
                    token = it
                }

                getAnimal(petId.toInt(), token).onEach { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.value = DetailState(
                                isLoading = true,
                                error = result.message ?: "Error inesperado"
                            )
                        }

                        is Resource.Loading -> {
                            _state.value = DetailState(
                                isLoading = true,
                                error = ""
                            )
                        }

                        is Resource.Success -> {
                            _state.value = DetailState(
                                pet = result.data,
                                isLoading = false
                            )
                        }
                    }
                }
            }
        }
    }
}