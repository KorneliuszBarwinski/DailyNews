package com.korneliuszbarwinski.dailynews.common.interceptors

import com.korneliuszbarwinski.dailynews.common.Constants
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder()
            .addQueryParameter("apiKey", Constants.API_KEY)
            .build()

        val requestBuilder = originalRequest.newBuilder()
            .url(url)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}