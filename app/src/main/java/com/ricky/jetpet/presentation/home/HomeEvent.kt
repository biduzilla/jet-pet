package com.ricky.jetpet.presentation.home

sealed interface HomeEvent {
    data object OnSwitch : HomeEvent
    data object LoadPets : HomeEvent
}