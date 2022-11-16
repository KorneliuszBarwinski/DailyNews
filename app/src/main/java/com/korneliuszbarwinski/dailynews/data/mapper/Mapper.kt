package com.korneliuszbarwinski.dailynews.data.mapper

import com.korneliuszbarwinski.dailynews.common.NewsException
import com.korneliuszbarwinski.dailynews.common.NewsExceptionCode
import com.korneliuszbarwinski.dailynews.data.remote.dto.ArticleDto
import com.korneliuszbarwinski.dailynews.data.remote.dto.NewsErrorDto
import com.korneliuszbarwinski.dailynews.data.remote.dto.NewsResponseDto
import com.korneliuszbarwinski.dailynews.data.remote.dto.SourceDto
import com.korneliuszbarwinski.dailynews.domain.model.Article
import com.korneliuszbarwinski.dailynews.domain.model.Source

fun NewsResponseDto.toListOfArticles(): List<Article> = articles.map{ it.toArticle() }

fun ArticleDto.toArticle(): Article = Article(
    author = this.author,
    content = this.content,
    description = this.description,
    publishedAt = this.publishedAt,
    source = this.source.toSource(),
    title = this.title,
    url = this.url,
    urlToImage = this.urlToImage
)

fun SourceDto.toSource(): Source = Source(
    id = this.id,
    name = this.name
)

fun NewsErrorDto.toNewsException() : NewsException = when (this.code){
    NewsExceptionCode.apiKeyDisabled.name -> NewsException(message, NewsExceptionCode.apiKeyDisabled)
    NewsExceptionCode.apiKeyInvalid.name -> NewsException(message, NewsExceptionCode.apiKeyInvalid)
    NewsExceptionCode.apiKeyMissing.name -> NewsException(message, NewsExceptionCode.apiKeyMissing)
    NewsExceptionCode.apiKeyExhausted.name -> NewsException(message, NewsExceptionCode.apiKeyExhausted)
    NewsExceptionCode.parameterInvalid.name -> NewsException(message, NewsExceptionCode.parameterInvalid)
    NewsExceptionCode.parametersMissing.name -> NewsException(message, NewsExceptionCode.parametersMissing)
    NewsExceptionCode.rateLimited.name -> NewsException(message, NewsExceptionCode.rateLimited)
    NewsExceptionCode.sourcesTooMany.name -> NewsException(message, NewsExceptionCode.sourcesTooMany)
    NewsExceptionCode.sourceDoesNotExist.name -> NewsException(message, NewsExceptionCode.sourceDoesNotExist)
    else -> NewsException(message, NewsExceptionCode.unexpectedError)
}