package com.ricky.jetpet.domain.paginator

interface PetPaginator<Page,Content> {
    suspend fun fetchNextPage()
    fun resetPage()
}