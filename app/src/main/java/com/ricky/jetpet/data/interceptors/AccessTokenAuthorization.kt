package com.ricky.jetpet.data.interceptors

import com.ricky.jetpet.data.token.AccessTokenRepository
import kotlinx.coroutines.runBlocking
import okhttp3.Authenticator
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import javax.inject.Inject

class AccessTokenAuthorization @Inject constructor(
    private val tokenRepository: AccessTokenRepository
) : Authenticator {

    private val Response.retryCount: Int
        get() {
            var currentResponse = priorResponse
            var result = 0

            while (currentResponse != null) {
                result++
                currentResponse = currentResponse.priorResponse
            }
            return result
        }

    override fun authenticate(route: Route?, response: Response): Request? {
        synchronized(tokenRepository.lock()) {
            return when {
                response.retryCount > 2 -> null
                else -> runBlocking {
                    response.createSignedRequest()
                }
            }
        }
    }

    private suspend fun Response.createSignedRequest(): Request? = try {
        tokenRepository.fetchAccessToken()
        request
    } catch (e: Exception) {
        e.printStackTrace()
        null
    }
}