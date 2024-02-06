package com.ricky.jetpet.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breeds(
    @SerialName("mixed")
    val mixed: Boolean,
    @SerialName("primary")
    val primary: String,
    @SerialName("secondary")
    val secondary: String,
    @SerialName("unknown")
    val unknown: Boolean
)