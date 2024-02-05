package com.ricky.jetpet.data.network.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Pagination(
    @SerialName("count_per_page")
    val countPerPage: Int,
    @SerialName("current_page")
    val currentPage: Int,
    @SerialName("_links")
    val links: LinksX,
    @SerialName("total_count")
    val totalCount: Int,
    @SerialName("total_pages")
    val totalPages: Int
)