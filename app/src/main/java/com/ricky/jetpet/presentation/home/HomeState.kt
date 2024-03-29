package com.ricky.jetpet.presentation.home

import com.ricky.jetpet.domain.model.Pet

data class HomeState(
    val pets: List<Pet> = emptyList(),
    val isLoading: Boolean = false,
    val page: Int = 1,
    val isFetchingPet: Boolean = false,
    val loadMoreButtonVisible: Boolean = true,
    val startInfiniteScrolling: Boolean = false,
    val isDark: Boolean = false,
    val error: String = "",
    val isError: Boolean = false
)
