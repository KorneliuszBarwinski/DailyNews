package com.korneliuszbarwinski.dailynews.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.korneliuszbarwinski.dailynews.common.Resource
import com.korneliuszbarwinski.dailynews.domain.model.Article
import com.korneliuszbarwinski.dailynews.domain.usecase.GetNewsUseCase
import com.korneliuszbarwinski.dailynews.domain.usecase.RefreshNewsUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val refreshNewsUseCase: RefreshNewsUseCase
) : ViewModel() {

    private val _latestNews = MutableLiveData<Resource<List<Article>>>()
    val latestNews: LiveData<Resource<List<Article>>>
        get() = _latestNews

    init {
        getLatestNews()
    }

    private fun getLatestNews() {
        _latestNews.value = Resource.Loading()
        viewModelScope.launch {
            _latestNews.value = getNewsUseCase.invoke()
        }
    }

    fun refreshNews() {
        _latestNews.value = Resource.Loading()
        viewModelScope.launch {
            _latestNews.value = refreshNewsUseCase.invoke()
        }
    }
}