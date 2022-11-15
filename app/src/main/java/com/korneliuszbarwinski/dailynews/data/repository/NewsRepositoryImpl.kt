package com.korneliuszbarwinski.dailynews.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_PAGE_SIZE
import com.korneliuszbarwinski.dailynews.data.paging.NewsPagingSource
import com.korneliuszbarwinski.dailynews.data.remote.NewsApi
import com.korneliuszbarwinski.dailynews.domain.model.Article
import com.korneliuszbarwinski.dailynews.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val newsApi: NewsApi
) : NewsRepository {
    override suspend fun getNews(): Flow<PagingData<Article>> {
        return Pager(
            config = PagingConfig(
                pageSize = DEFAULT_PAGE_SIZE,
                enablePlaceholders = false
            ),
            pagingSourceFactory = {
                NewsPagingSource(api = newsApi)
            }
        ).flow
    }
}