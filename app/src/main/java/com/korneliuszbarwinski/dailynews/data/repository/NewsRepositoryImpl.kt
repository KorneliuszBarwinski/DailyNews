package com.korneliuszbarwinski.dailynews.data.repository

import com.korneliuszbarwinski.dailynews.common.Resource
import com.korneliuszbarwinski.dailynews.data.mapper.toListOfArticles
import com.korneliuszbarwinski.dailynews.data.remote.NewsApi
import com.korneliuszbarwinski.dailynews.domain.model.Article
import com.korneliuszbarwinski.dailynews.domain.repository.NewsRepository
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val api: NewsApi
) : NewsRepository {
    override suspend fun getNews(): Resource<List<Article>> {
        return try {
            val response = api.getArticles()
            Resource.Success(response.toListOfArticles())
        } catch(e: IOException) {
            e.printStackTrace()
            Resource.Error(
                message = "Can't load articles"
            )
        } catch(e: HttpException) {
            e.printStackTrace()
            Resource.Error(
                message = "Can't load articles"
            )
        } catch(e: Exception) {
            e.printStackTrace()
            Resource.Error(
                message = "Can't load articles"
            )
        }
    }
}