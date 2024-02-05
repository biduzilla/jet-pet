package com.ricky.jetpet.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiAnimals(
    @SerialName("animals")
    val animals: List<Animal>,
    @SerialName("pagination")
    val pagination: Pagination
)