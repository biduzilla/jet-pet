package com.ricky.jetpet.apresentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.paginator.LoadingStateListener
import com.ricky.jetpet.domain.paginator.PetPaginatorImpl
import com.ricky.jetpet.domain.repository.PetRepository
import com.ricky.jetpet.utils.ResourceHolder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataStoreUtil: DataStoreUtil,
    private val petRepository: PetRepository
) : ViewModel(), LoadingStateListener<ResourceHolder<List<Pet>>> {

    private var _state = MutableStateFlow(HomeState())
    val state = _state.asStateFlow()

    companion object {
        const val TAG = "myModel"
    }

    private val petPaginator = PetPaginatorImpl(
        initialKey = getPage(_state.value.animals.data),
        loadingState = this,
        onRequest = { page ->
            if (_state.value.isFetchingPet) return@PetPaginatorImpl ResourceHolder.Loading()

            val pet = fetchAnimals(page)
            pet
        },
        getNextPage = { result ->
            getPage(result.data)
        }
    )

    init {
        viewModelScope.launch {
            dataStoreUtil.getTheme().collect { isDark ->
                _state.update {
                    it.copy(
                        isDark = isDark
                    )
                }
            }
            loadNextPetsPage()
        }

    }

    private suspend fun loadNextPetsPage() {
        petPaginator.fetchNextPage()
    }

    private suspend fun fetchAnimals(page: Int): ResourceHolder<List<Pet>> {
        return petRepository.getAnimals(page)
    }

    private fun getPage(pageSource: List<Pet>?): Int {
        return if (pageSource?.isEmpty() == true) {
            pageSource[pageSource.lastIndex].currentPage + 1
        } else {
            1
        }
    }

    override fun onLoadingStateChange(isLoading: Boolean) {
        _state.update {
            it.copy(
                isFetchingPet = isLoading
            )
        }
    }

    override fun onError(error: Throwable) {
        Log.e(TAG, "onError: Fetching Pet error", error)
        _state.update {
            it.copy(
                isError = true,
                error = error.message.toString()
            )
        }
    }

    override fun onDataFetched(data: ResourceHolder<List<Pet>>) {
        _state.value = _state.value.updateAnimal(data)
    }

    private fun HomeState.updateAnimal(newData: ResourceHolder<List<Pet>>): HomeState {
        return when (newData) {
            is ResourceHolder.Error -> {
                copy(animals = newData)
            }

            is ResourceHolder.Success -> {
                val updateDate = this.animals.data?.combineData(newData.data!!) ?: newData
                copy(
                    animals = updateDate,
                    isError = false
                )
            }

            else -> this
        }
    }

    private fun <Data> List<Data>.combineData(newList: List<Data>): ResourceHolder<List<Data>> {
        return ResourceHolder.Success(data = this.union(newList).toList())
    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            HomeEvent.OnSwitch -> {
                viewModelScope.launch {
                    dataStoreUtil.saveTheme(isDark = !_state.value.isDark)
                }
            }

            is HomeEvent.OnInfiniteScrollingChange -> TODO()
            HomeEvent.OnLoadNextPage -> TODO()
        }
    }


}