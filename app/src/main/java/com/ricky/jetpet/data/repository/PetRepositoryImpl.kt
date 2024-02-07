package com.ricky.jetpet.data.repository

import android.util.Log
import com.ricky.jetpet.data.network.models.toPet
import com.ricky.jetpet.data.network.retrofit.PetFinderApiService
import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.repository.PetRepository
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val apiService: PetFinderApiService,
) : PetRepository {
    override suspend fun getAnimals(page: Int, token: String): List<Pet> {

        val data = apiService.getAnimals(page = page, token = token)
        val pets = data.animals.map { it.toPet() }

        pets.forEach { animal ->
            animal.currentPage = data.pagination.currentPage
        }
        Log.i("infoteste", "getAnimals: $pets")
        return pets
    }

    override suspend fun getAnimalById(id: Int, token: String): Pet {
        return apiService.getAnimal(id = id, token = token).toPet()
    }
}