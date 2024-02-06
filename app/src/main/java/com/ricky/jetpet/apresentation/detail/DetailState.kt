package com.ricky.jetpet.apresentation.detail

import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.utils.ResourceHolder

data class DetailState(
    val pet: ResourceHolder<Pet> = ResourceHolder.Loading(),
)