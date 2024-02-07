package com.ricky.jetpet.domain.use_case

import com.ricky.jetpet.domain.model.Pet
import com.ricky.jetpet.domain.repository.PetRepository
import com.ricky.jetpet.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAnimals @Inject constructor(private val repository: PetRepository) {
    operator fun invoke(page: Int, token: String): Flow<Resource<List<Pet>>> = flow {
        try {
            emit(Resource.Loading<List<Pet>>())
            val pets = repository.getAnimals(page = page, token = token)
            emit(Resource.Success<List<Pet>>(pets))
        } catch (e: HttpException) {
            emit(Resource.Error<List<Pet>>(message = e.localizedMessage ?: "Error inesperado"))
        } catch (e: IOException) {
            emit(Resource.Error<List<Pet>>(message = "Cheque sua conexão com a internet"))
        }
    }
}