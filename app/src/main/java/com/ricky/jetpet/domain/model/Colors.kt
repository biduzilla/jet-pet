package com.ricky.jetpet.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Colors(
    @SerialName("primary")
    val primary: Any?,
    @SerialName("secondary")
    val secondary: Any?,
    @SerialName("tertiary")
    val tertiary: Any?
)