package com.ricky.jetpet.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LinksX(
    @SerialName("next")
    val next: Next,
    @SerialName("previous")
    val previous: Previous
)