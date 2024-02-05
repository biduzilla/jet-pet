package com.ricky.jetpet.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Links(
    @SerialName("organization")
    val organization: Organization,
    @SerialName("self")
    val self: Self,
    @SerialName("type")
    val type: Type
)