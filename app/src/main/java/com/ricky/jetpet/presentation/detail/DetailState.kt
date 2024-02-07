package com.ricky.jetpet.presentation.detail

import com.ricky.jetpet.domain.model.Pet

data class DetailState(
    val pet: Pet? = null,
    val isLoading: Boolean = false,
    val error: String = "",
)