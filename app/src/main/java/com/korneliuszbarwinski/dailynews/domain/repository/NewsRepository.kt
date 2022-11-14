package com.korneliuszbarwinski.dailynews.domain.repository

import com.korneliuszbarwinski.dailynews.common.Resource
import com.korneliuszbarwinski.dailynews.domain.model.Article

interface NewsRepository {
    suspend fun getNews(): Resource<List<Article>>
}