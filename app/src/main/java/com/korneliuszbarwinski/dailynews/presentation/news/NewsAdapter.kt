package com.korneliuszbarwinski.dailynews.presentation.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.korneliuszbarwinski.dailynews.R
import com.korneliuszbarwinski.dailynews.databinding.ListItemArticleBinding
import com.korneliuszbarwinski.dailynews.domain.model.Article

class NewsAdapter :
    PagingDataAdapter<Article, NewsAdapter.NewsViewHolder>(ArticleDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding =
            ListItemArticleBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val article: Article? = getItem(position)
        if (article != null) holder.bindData(article)
    }

    inner class NewsViewHolder(private val binding: ListItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindData(article: Article) {
            binding.apply {
                articleTittleTV.text = article.title
                articleSourceTV.text = article.source.name
                articleDescriptionTV.text = article.description
                articlePublishedAtTV.text = article.publishedAt

                Glide.with(this.root)
                    .load(article.urlToImage)
                    .centerCrop()
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_image_error)
                    .into(articleImageIV)
            }
        }
    }
}

class ArticleDiffCallback : DiffUtil.ItemCallback<Article>() {
    override fun areItemsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: Article, newItem: Article): Boolean {
        return oldItem == newItem
    }
}