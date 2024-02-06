package com.ricky.jetpet.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Colors(
    @SerialName("primary")
    val primary: String?,
    @SerialName("secondary")
    val secondary: Any?,
    @SerialName("tertiary")
    val tertiary: Any?
)