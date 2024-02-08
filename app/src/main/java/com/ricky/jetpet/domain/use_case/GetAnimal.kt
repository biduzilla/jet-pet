package com.ricky.jetpet.domain.use_case

import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.repository.PetRepository
import com.ricky.jetpet.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okio.IOException
import retrofit2.HttpException
import javax.inject.Inject

class GetAnimal @Inject constructor(private val repository: PetRepository) {
    operator fun invoke(petId: Int, token: String): Flow<Resource<Pet>> = flow {
        try {
            emit(Resource.Loading<Pet>())
            val pet = repository.getAnimalById(petId, token)
            emit(Resource.Success<Pet>(pet ?: Pet(currentPage = 0)))
        } catch (e: HttpException) {
            emit(Resource.Error<Pet>(e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error<Pet>("Cheque sua conexão com a internet"))
        }
    }
}