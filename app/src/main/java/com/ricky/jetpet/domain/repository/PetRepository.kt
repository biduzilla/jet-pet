package com.ricky.jetpet.domain.repository

import com.ricky.jetpet.domain.model.Pet

interface PetRepository {

    suspend fun getAnimals(page:Int):List<Pet>
}