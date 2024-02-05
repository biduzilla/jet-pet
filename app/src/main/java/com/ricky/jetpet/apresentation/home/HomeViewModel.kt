package com.ricky.jetpet.apresentation.home

import androidx.compose.runtime.MutableState
import androidx.lifecycle.ViewModel
import com.ricky.jetpet.domain.DummyPetDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class HomeViewModel : ViewModel() {

    private val _state = MutableStateFlow(HomeState())
    private val state = _state.asStateFlow()

    init {
        _state.update {
            it.copy(
                pets = DummyPetDataSource.dogList
            )
        }
    }

    fun onEvent(event:HomeEvent){
        when(event){
            HomeEvent.OnSwitch -> {

            }
        }
    }
}