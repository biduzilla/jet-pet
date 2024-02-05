package com.ricky.jetpet.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    @SerialName("next")
    val next: Next,
    @SerialName("previous")
    val previous: Previous
)