package com.korneliuszbarwinski.dailynews.presentation.news

import androidx.recyclerview.widget.DiffUtil
import com.korneliuszbarwinski.dailynews.domain.model.Article

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}