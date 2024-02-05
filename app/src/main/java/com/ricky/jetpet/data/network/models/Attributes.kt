package com.ricky.jetpet.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Attributes(
    @SerialName("declawed")
    val declawed: Any?,
    @SerialName("house_trained")
    val houseTrained: Boolean,
    @SerialName("shots_current")
    val shotsCurrent: Boolean,
    @SerialName("spayed_neutered")
    val spayedNeutered: Boolean,
    @SerialName("special_needs")
    val specialNeeds: Boolean
)