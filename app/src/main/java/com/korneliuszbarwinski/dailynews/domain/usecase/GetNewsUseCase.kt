package com.korneliuszbarwinski.dailynews.domain.usecase

import com.korneliuszbarwinski.dailynews.common.Resource
import com.korneliuszbarwinski.dailynews.domain.model.Article
import com.korneliuszbarwinski.dailynews.domain.repository.NewsRepository
import javax.inject.Inject

class GetNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): Resource<List<Article>> = repository.getNews()
}