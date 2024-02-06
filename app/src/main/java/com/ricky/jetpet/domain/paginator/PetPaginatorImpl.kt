package com.ricky.jetpet.domain.paginator

class PetPaginatorImpl<Page, Result> constructor(
    private val initialKey: Page,
    private val loadingState: LoadingStateListener<Result>,
    private val onRequest: suspend (nextPage: Page) -> Result,
    private val getNextPage: (Result) -> Page
) : PetPaginator<Page, Result> {

    private var currentPage = initialKey
    override suspend fun fetchNextPage() {
        try {
            val result = onRequest(currentPage)
            loadingState.onLoadingStateChange(true)
            currentPage = getNextPage(result)
            loadingState.onDataFetched(result)
            loadingState.onLoadingStateChange(false)

        } catch (e: Exception) {
            loadingState.onLoadingStateChange(false)
            loadingState.onError(e)
        }
    }

    override fun resetPage() {
        currentPage = initialKey
    }
}

interface LoadingStateListener<T> {
    fun onLoadingStateChange(isLoading: Boolean)
    fun onDataFetched(data: T)
    fun onError(error: Throwable)
}