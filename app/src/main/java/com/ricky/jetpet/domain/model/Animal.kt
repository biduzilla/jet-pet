package com.ricky.jetpet.domain.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Animal(
    @SerialName("age")
    val age: String,
    @SerialName("attributes")
    val attributes: Attributes,
    @SerialName("breeds")
    val breeds: Breeds,
    @SerialName("coat")
    val coat: Any?,
    @SerialName("colors")
    val colors: Colors,
    @SerialName("contact")
    val contact: Contact,
    @SerialName("description")
    val description: String,
    @SerialName("environment")
    val environment: Environment,
    @SerialName("gender")
    val gender: String,
    @SerialName("id")
    val id: Int,
    @SerialName("_links")
    val links: Links,
    @SerialName("name")
    val name: String,
    @SerialName("organization_id")
    val organizationId: String,
    @SerialName("photos")
    val photos: List<Photo>,
    @SerialName("published_at")
    val publishedAt: String,
    @SerialName("size")
    val size: String,
    @SerialName("species")
    val species: String,
    @SerialName("status")
    val status: String,
    @SerialName("tags")
    val tags: List<String>,
    @SerialName("type")
    val type: String,
    @SerialName("url")
    val url: String,
    @SerialName("videos")
    val videos: List<Video>
)