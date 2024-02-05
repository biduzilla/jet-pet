package com.ricky.jetpet.apresentation.home

import com.ricky.jetpet.domain.model.Pet

data class HomeState(
    val pets: List<Pet> = emptyList(),
    val isDark: Boolean = false
)
