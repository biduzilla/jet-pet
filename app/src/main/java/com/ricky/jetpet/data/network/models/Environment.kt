package com.ricky.jetpet.data.network.models


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