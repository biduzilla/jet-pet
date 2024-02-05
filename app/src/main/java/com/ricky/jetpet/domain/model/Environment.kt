package com.ricky.jetpet.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Environment(
    @SerialName("cats")
    val cats: Boolean,
    @SerialName("children")
    val children: Boolean,
    @SerialName("dogs")
    val dogs: Boolean
)