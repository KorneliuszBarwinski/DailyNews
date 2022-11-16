package com.korneliuszbarwinski.dailynews.domain.repository

import androidx.paging.PagingData
import com.korneliuszbarwinski.dailynews.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    suspend fun getNews(): Flow<PagingData<Article>>

    suspend fun getNewsWithQuery(query: String): Flow<PagingData<Article>>
}