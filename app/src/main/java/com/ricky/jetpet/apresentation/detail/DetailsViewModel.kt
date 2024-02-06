package com.ricky.jetpet.apresentation.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.jetpet.domain.repository.PetRepository
import com.ricky.jetpet.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val saveStateHandle: SavedStateHandle,
    private val petRepository: PetRepository
) :
    ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch {
            saveStateHandle.get<String>(Constants.PARAM_PET_ID)?.let { petId ->
                val pet = petRepository.getAnimalById(petId.toInt())

                _state.update {
                    it.copy(
                        pet = pet
                    )
                }
            }
        }
    }
}