package com.korneliuszbarwinski.dailynews.data.mapper

import com.korneliuszbarwinski.dailynews.data.remote.dto.ArticleDto
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