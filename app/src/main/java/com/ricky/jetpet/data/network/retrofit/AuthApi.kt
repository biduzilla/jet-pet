package com.ricky.jetpet.data.network.retrofit

import com.ricky.jetpet.data.network.models.AccessToken
import com.ricky.jetpet.utils.Constants
import com.ricky.jetpet.utils.Constants.API_KEY
import com.ricky.jetpet.utils.Constants.SECRET_KEY
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface AuthApi {
    @POST(Constants.AUTH_END_POINT)
    @FormUrlEncoded
    suspend fun getAuthToken(
        @Field(Constants.CLIENT_ID) clientId: String = API_KEY,
        @Field(Constants.CLIENT_SECRET) clientSecret: String = SECRET_KEY,
        @Field("grant_type") grantType: String = "client_credentials",
    ): AccessToken
}