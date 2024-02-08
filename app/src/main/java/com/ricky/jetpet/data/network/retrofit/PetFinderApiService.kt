package com.ricky.jetpet.data.network.retrofit

import com.ricky.jetpet.data.network.models.Animal
import com.ricky.jetpet.data.network.models.ApiAnimals
import com.ricky.jetpet.utils.Constants.BASE_END_POINT
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query

interface PetFinderApiService {

    @GET(BASE_END_POINT)
    suspend fun getAnimals(
        @Header("Authorization") token: String,
        @Query("page") page: Int
    ): Response<ApiAnimals>

    @GET("$BASE_END_POINT/{id}")
    suspend fun getAnimal(@Header("Authorization") token: String, @Path("id") id: Int): Animal
}