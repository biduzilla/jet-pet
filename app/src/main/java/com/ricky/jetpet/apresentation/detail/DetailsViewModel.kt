package com.ricky.jetpet.apresentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.ricky.jetpet.common.Constants
import com.ricky.jetpet.domain.DummyPetDataSource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(saveStateHandle: SavedStateHandle) : ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        saveStateHandle.get<Int>(Constants.PARAM_PET_ID)?.let { petId ->
            val pet = DummyPetDataSource.dogList.find { p -> p.id == petId }
            pet?.let {
                _state.update {
                    it.copy(
                        pet = pet
                    )
                }
            }
        }
    }
}