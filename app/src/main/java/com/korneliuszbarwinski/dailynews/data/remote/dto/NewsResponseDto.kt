package com.korneliuszbarwinski.dailynews.data.remote.dto

data class NewsResponseDto(
    val articles: List<ArticleDto>,
    val status: String,
    val totalResults: Int
)
