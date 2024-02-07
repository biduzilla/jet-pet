package com.ricky.jetpet.data.network.interceptors

import android.content.Context
import com.ricky.jetpet.data.local.DataStoreUtil
import com.ricky.jetpet.data.network.token.AccessTokenRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AccessInterceptor(private val tokenRepository: AccessTokenRepository) : Interceptor {
    companion object {
        const val TAG = "AccessTokenRepository"
    }


    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest(): Request = runBlocking {
        var token: String? = null


        tokenRepository.token().collect {
            token = it
        }

        newBuilder()
            .addHeader(
                "Authorization",
                "Bearer $token"
            )
            .build()
    }
}