package com.ricky.jetpet.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Breeds(
    @SerialName("mixed")
    val mixed: Boolean,
    @SerialName("primary")
    val primary: String,
    @SerialName("secondary")
    val secondary: Any?,
    @SerialName("unknown")
    val unknown: Boolean
)