package com.ricky.jetpet.data.network.retrofit

import com.ricky.jetpet.common.Constants.API_KEY
import com.ricky.jetpet.common.Constants.AUTH_END_POINT
import com.ricky.jetpet.common.Constants.BASE_END_POINT
import com.ricky.jetpet.common.Constants.CLIENT_ID
import com.ricky.jetpet.common.Constants.CLIENT_SECRET
import com.ricky.jetpet.common.Constants.SECRET_KEY
import com.ricky.jetpet.data.network.models.AccessToken
import com.ricky.jetpet.data.network.models.ApiAnimals
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface PetFinderApiService {

    @GET(BASE_END_POINT)
    suspend fun getAnimals(
        @Query("page") page: Int
    ): ApiAnimals

    @POST(AUTH_END_POINT)
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field(CLIENT_ID) clientId: String = API_KEY,
        @Field(CLIENT_SECRET) clientSecret: String = SECRET_KEY,
        @Field("grant_type") grantType: String = "client_Credentials",
    ): AccessToken
}