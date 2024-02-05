package com.ricky.jetpet.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Contact(
    @SerialName("address")
    val address: Address,
    @SerialName("email")
    val email: String,
    @SerialName("phone")
    val phone: String
)