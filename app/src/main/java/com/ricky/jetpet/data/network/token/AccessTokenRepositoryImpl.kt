package com.ricky.jetpet.data.network.token

import android.util.Log
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.data.network.models.AccessToken
import com.ricky.jetpet.data.network.retrofit.AuthApi
import com.ricky.jetpet.data.network.retrofit.PetFinderApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AccessTokenRepositoryImpl @Inject constructor(
    private val dataStore: DataStoreUtil,
    private val api: AuthApi
) : AccessTokenRepository {
    companion object {
        private const val TAG = "AccessTokenRepositoryImpl"
    }

    override suspend fun fetchAccessToken(): AccessToken {
        val accessToken = api.getAuthToken()
        Log.i("infoteste", "fetchAccessToken: $accessToken")
        dataStore.saveToken("${accessToken.tokenType} ${accessToken.accessToken}")
        return accessToken
    }

    override fun refreshToken(): AccessToken? {
        return null
    }

    override suspend fun saveToken(token: String?) {

    }

    override fun token(): Flow<String?> = dataStore.getToken()

    override fun lock(): Any {
        return this
    }
}