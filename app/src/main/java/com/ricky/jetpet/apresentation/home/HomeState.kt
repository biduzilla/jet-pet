package com.ricky.jetpet.apresentation.home

import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.utils.ResourceHolder

data class HomeState(
    val animals: ResourceHolder<List<Pet>> = ResourceHolder.Loading(),
    val isFetchingPet: Boolean = false,
    val loadMoreButtonVisible: Boolean = true,
    val startInfiniteScrolling: Boolean = false,
    val isDark: Boolean = false,
    val error: String = "",
    val isError: Boolean = false
)
