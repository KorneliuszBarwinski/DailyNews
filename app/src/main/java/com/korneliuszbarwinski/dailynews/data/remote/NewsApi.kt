package com.korneliuszbarwinski.dailynews.data.remote

import com.korneliuszbarwinski.dailynews.common.Constants.API_KEY
import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_COUNTRY_CODE
import com.korneliuszbarwinski.dailynews.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("country") countryCode: String = DEFAULT_COUNTRY_CODE
    ): NewsResponseDto
}