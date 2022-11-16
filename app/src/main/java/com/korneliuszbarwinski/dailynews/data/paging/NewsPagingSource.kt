package com.korneliuszbarwinski.dailynews.data.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.korneliuszbarwinski.dailynews.common.Constants.DEFAULT_PAGE
import com.korneliuszbarwinski.dailynews.data.mapper.toListOfArticles
import com.korneliuszbarwinski.dailynews.data.remote.NewsApi
import com.korneliuszbarwinski.dailynews.domain.model.Article

class NewsPagingSource(
    private val api: NewsApi
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
            val response = api.getArticles(page = pageIndex)
            LoadResult.Page(
                data = response.toListOfArticles(),
                prevKey = if (pageIndex == DEFAULT_PAGE) null else pageIndex - 1,
                nextKey = if (response.articles.isEmpty()) null else pageIndex + 1,
            )
        } catch (e: Exception) {
            e.printStackTrace()
            LoadResult.Error(e)
        }
    }
}