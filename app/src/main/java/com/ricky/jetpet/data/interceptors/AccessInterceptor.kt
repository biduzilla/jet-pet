package com.ricky.jetpet.data.interceptors

import com.ricky.jetpet.data.token.AccessTokenRepository
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import javax.inject.Inject

class AccessInterceptor @Inject constructor(private val tokenRepository: AccessTokenRepository) :
    Interceptor {
    companion object {
        const val TAG = "myApp"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val newRequest = chain.request().signedRequest()
        return chain.proceed(newRequest)
    }

    private fun Request.signedRequest(): Request {
        return newBuilder()
            .addHeader(
                "Authorization",
                "Bearer ${tokenRepository.token()}"
            )
            .build()
    }
}