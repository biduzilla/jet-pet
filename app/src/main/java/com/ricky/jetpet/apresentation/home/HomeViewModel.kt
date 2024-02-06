package com.ricky.jetpet.apresentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.jetpet.data.local.DataStoreUtil
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val dataStoreUtil: DataStoreUtil) : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            dataStoreUtil.getTheme().collect { isDark ->
                _state.update {
                    it.copy(
                        pets = DummyPetDataSource.dogList,
                        isDark = isDark
                    )
                }
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
        }
    }
}