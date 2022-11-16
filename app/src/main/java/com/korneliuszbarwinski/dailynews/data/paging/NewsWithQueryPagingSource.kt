package com.korneliuszbarwinski.dailynews.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.google.gson.Gson
import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_PAGE
import com.korneliuszbarwinski.dailynews.common.NewsException
import com.korneliuszbarwinski.dailynews.common.NewsExceptionCode
import com.korneliuszbarwinski.dailynews.data.mapper.toListOfArticles
import com.korneliuszbarwinski.dailynews.data.mapper.toNewsException
import com.korneliuszbarwinski.dailynews.data.remote.NewsApi
import com.korneliuszbarwinski.dailynews.data.remote.dto.NewsErrorDto
import com.korneliuszbarwinski.dailynews.domain.model.Article
import retrofit2.HttpException

class NewsWithQueryPagingSource(
    private val api: NewsApi,
    private val query: String
) : PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        val pageIndex = params.key ?: DEFAULT_PAGE

        return try {
            val response = api.getArticlesWithQuery(page = pageIndex, q = query)
            LoadResult.Page(
                data = response.toListOfArticles(),
                prevKey = if (pageIndex == DEFAULT_PAGE) null else pageIndex - 1,
                nextKey = if (response.articles.isEmpty()) null else pageIndex + 1,
            )
        } catch (e: Exception) {
            when (e) {
                is HttpException -> {
                    LoadResult.Error(e.handleHttpException())
                }
                else -> {
                    LoadResult.Error(
                        NewsException(
                            "This exception is unhandled",
                            NewsExceptionCode.unhandledException
                        )
                    )
                }
            }
        }
    }

    private fun HttpException.handleHttpException(): NewsException {
        return try {
            Gson().fromJson(this.response()?.errorBody()?.string()!!, NewsErrorDto::class.java).toNewsException()
        } catch (e: Exception) {
            NewsException("There was a problem with parsing HttpException", NewsExceptionCode.parsingError)
        }
    }
}