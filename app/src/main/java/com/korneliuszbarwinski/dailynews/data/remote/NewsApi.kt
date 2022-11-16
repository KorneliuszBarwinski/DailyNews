package com.korneliuszbarwinski.dailynews.data.remote

import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_COUNTRY_CODE
import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_LANGUAGE
import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_PAGE
import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_PAGE_SIZE
import com.korneliuszbarwinski.dailynews.data.remote.dto.NewsResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {
    @GET("top-headlines")
    suspend fun getArticles(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("country") countryCode: String = DEFAULT_COUNTRY_CODE
    ): NewsResponseDto

    @GET("everything")
    suspend fun getArticlesWithQuery(
        @Query("page") page: Int = DEFAULT_PAGE,
        @Query("pageSize") pageSize: Int = DEFAULT_PAGE_SIZE,
        @Query("language") countryCode: String = DEFAULT_LANGUAGE,
        @Query("q") q: String
    ): NewsResponseDto
}