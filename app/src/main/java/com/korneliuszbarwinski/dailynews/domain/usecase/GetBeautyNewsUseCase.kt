package com.korneliuszbarwinski.dailynews.domain.usecase

import androidx.paging.PagingData
import com.korneliuszbarwinski.dailynews.domain.model.Article
import com.korneliuszbarwinski.dailynews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBeautyNewsUseCase @Inject constructor(
    private val repository: NewsRepository
) {
    suspend operator fun invoke(): Flow<PagingData<Article>> = repository.getNewsWithQuery("beauty")
}