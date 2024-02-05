package com.ricky.jetpet.data.token

import com.ricky.jetpet.data.network.models.AccessToken
import kotlinx.coroutines.flow.Flow

interface AccessTokenRepository {
    suspend fun fetchAccessToken(): AccessToken?

    fun refreshToken(): AccessToken?

    suspend fun saveToken(token: String?)

    fun token(): Flow<String?>

    fun lock(): Any
}