package com.ricky.jetpet.data.repository

import android.util.Log
import com.ricky.jetpet.data.network.models.toPet
import com.ricky.jetpet.data.network.retrofit.PetFinderApiService
import com.ricky.jetpet.data.network.token.AccessTokenRepository
import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.repository.PetRepository
import com.ricky.jetpet.presentation.home.HomeViewModel
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val apiService: PetFinderApiService,
    private val tokenRepository: AccessTokenRepository
) : PetRepository {
    override suspend fun getAnimals(page: Int, token: String): List<Pet> {
        val data = apiService.getAnimals(page = page, token = token)

        Log.i(HomeViewModel.TAG, "getAnimals ${data.code()}")
        if (data.isSuccessful) {
            data.body()?.let { petResponse ->
                val pets = petResponse.animals.map { it.toPet() }

                pets.forEach { animal ->
                    animal.currentPage = petResponse.pagination.currentPage
                }
                return pets
            }
        } else if (data.code() == 401) {
            tokenRepository.fetchAccessToken()
        }
        return emptyList()
    }

    override suspend fun getAnimalById(id: Int, token: String): Pet {
        return apiService.getAnimal(id = id, token = token).toPet()
    }
}