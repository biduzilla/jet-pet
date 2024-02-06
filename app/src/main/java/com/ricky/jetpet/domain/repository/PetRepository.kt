package com.ricky.jetpet.domain.repository

import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.utils.ResourceHolder

interface PetRepository {
    suspend fun getAnimals(page: Int): ResourceHolder<List<Pet>>
    suspend fun getAnimalById(id: Int): ResourceHolder<Pet>
}