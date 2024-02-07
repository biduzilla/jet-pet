package com.ricky.jetpet.domain.repository

import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.utils.Resource

interface PetRepository {
    suspend fun getAnimals(page: Int, token: String): List<Pet>
    suspend fun getAnimalById(id: Int, token: String): Pet
}