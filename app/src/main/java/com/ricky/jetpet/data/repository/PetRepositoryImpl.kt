package com.ricky.jetpet.data.repository

import com.ricky.jetpet.data.network.mappers.AnimalApiMapper
import com.ricky.jetpet.data.network.mappers.PetApiMapper
import com.ricky.jetpet.data.network.models.Animal
import com.ricky.jetpet.data.network.models.ApiAnimals
import com.ricky.jetpet.data.network.retrofit.PetFinderApiService
import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.repository.PetRepository
import com.ricky.jetpet.utils.ResourceHolder
import javax.inject.Inject

class PetRepositoryImpl @Inject constructor(
    private val apiService: PetFinderApiService,
    private val petMapper: PetApiMapper<List<Pet>, ApiAnimals>,
    private val animalMapper: AnimalApiMapper<Pet, Animal>
) : PetRepository {
    override suspend fun getAnimals(page: Int): ResourceHolder<List<Pet>> {
        return try {
            val data = apiService.getAnimals(page)

            ResourceHolder.Success(petMapper.mapToDomain(data))
        } catch (e: Exception) {
            ResourceHolder.Error(e.cause)
        }
    }

    suspend fun getAnimalById(id: Int): ResourceHolder<Pet> {
        return try {
            val data = apiService.getAnimal(id)

            ResourceHolder.Success(animalMapper.mapToDomain(data))
        } catch (e: Exception) {
            ResourceHolder.Error(e.cause)
        }
    }
}