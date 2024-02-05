package com.ricky.jetpet.apresentation.home

sealed interface HomeEvent {
    object OnSwitch : HomeEvent
}