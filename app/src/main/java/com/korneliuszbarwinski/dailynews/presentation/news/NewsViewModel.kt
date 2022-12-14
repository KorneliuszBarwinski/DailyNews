package com.korneliuszbarwinski.dailynews.presentation.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.korneliuszbarwinski.dailynews.domain.model.Article
import com.korneliuszbarwinski.dailynews.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel
@Inject constructor(
    private val getNewsUseCase: GetNewsUseCase,
    private val refreshNewsUseCase: RefreshNewsUseCase,
    private val getBeautyNewsUseCase: GetBeautyNewsUseCase,
    private val getBusinessNewsUseCase: GetBusinessNewsUseCase,
    private val getPoliticsNewsUseCase: GetPoliticsNewsUseCase,
    private val getSportNewsUseCase: GetSportNewsUseCase,
    private val getTechnologyNewsUseCase: GetTechnologyNewsUseCase
) : ViewModel() {

    private val _latestNews = MutableLiveData<PagingData<Article>>()
    val latestNews: LiveData<PagingData<Article>>
        get() = _latestNews

    init {
        getLatestNews()
    }

    private fun getLatestNews() = viewModelScope.launch {
        getNewsUseCase.invoke().cachedIn(viewModelScope).collect{
            _latestNews.value = it
        }
    }

    fun refreshNews() = viewModelScope.launch {
        refreshNewsUseCase.invoke().cachedIn(viewModelScope).collect{
            _latestNews.value = it
        }
    }

    fun getBeautyNews() = viewModelScope.launch {
        getBeautyNewsUseCase.invoke().cachedIn(viewModelScope).collect{
            _latestNews.value = it
        }
    }

    fun getBusinessNews() = viewModelScope.launch {
        getBusinessNewsUseCase.invoke().cachedIn(viewModelScope).collect{
            _latestNews.value = it
        }
    }

    fun getPoliticsNews() = viewModelScope.launch {
        getPoliticsNewsUseCase.invoke().cachedIn(viewModelScope).collect{
            _latestNews.value = it
        }
    }

    fun getSportNews() = viewModelScope.launch {
        getSportNewsUseCase.invoke().cachedIn(viewModelScope).collect{
            _latestNews.value = it
        }
    }

    fun getTechnologyNews() = viewModelScope.launch {
        getTechnologyNewsUseCase.invoke().cachedIn(viewModelScope).collect{
            _latestNews.value = it
        }
    }

}