package com.ricky.jetpet.apresentation.home

sealed interface HomeEvent {
    object OnSwitch : HomeEvent
    object OnLoadNextPage : HomeEvent
    data class OnInfiniteScrollingChange(val isActive: Boolean) : HomeEvent
}