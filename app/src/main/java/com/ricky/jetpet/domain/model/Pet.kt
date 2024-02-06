package com.ricky.jetpet.domain.model

data class Pet(
    val id: String = "",
    val age: String = "",
    val breeds: String = "",
    val colors: String = "",
    val contact: PewOwnerContacts = PewOwnerContacts(),
    val description: String = "",
    val distance: String = "",
    val gender: String = "",
    val name: String = "",
    val photos: List<PetPhoto> = emptyList(),
    val size: String = "",
    val species: String = "",
    val status: String = "",
    val tags: List<String> = emptyList(),
    val type: String = "",
    val currentPage: Int
)
