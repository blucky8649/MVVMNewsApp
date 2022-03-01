package com.example.mvvmnewsapp.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvvmnewsapp.models.Article
import com.example.mvvmnewsapp.models.NewsResponse
import com.example.mvvmnewsapp.repository.NewsRepository
import com.example.mvvmnewsapp.util.Constants.Companion.COUNTRY
import com.example.mvvmnewsapp.util.Resource
import kotlinx.coroutines.launch
import retrofit2.Response

class NewsViewModel(
    val newsRepository: NewsRepository
) : ViewModel() {
    val breakingNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var breakingNewsPage = 1
    var breakingNewsResponse: NewsResponse? = null

    val searchNews: MutableLiveData<Resource<NewsResponse>> = MutableLiveData()
    var searchNewsPage = 1
    var searchNewsResponse: NewsResponse? = null

    init {
        getBreakingNews(COUNTRY)
    }

    fun getBreakingNews(countryCode: String) = viewModelScope.launch {
        // emit the loading state
        breakingNews.postValue(Resource.Loading())
        val response = newsRepository.getBreakingNews(countryCode, breakingNewsPage)
        breakingNews.postValue(handleBreakingNewsResponse(response))
    }

    fun searchNews(searchQuery: String) = viewModelScope.launch {
        searchNews.postValue(Resource.Loading())
        val response = newsRepository.searchNews(searchQuery, searchNewsPage)
        searchNews.postValue(handleSearchResponse(response))
    }

    private fun handleBreakingNewsResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { response ->
                breakingNewsPage++ // 스크롤을 내리면 다음 페이지의 기사들이 나올수 있도록 구현
                if (breakingNewsResponse == null) {
                    breakingNewsResponse = response
                } else {
                    val oldArticles = breakingNewsResponse?.articles
                    val newArticles = response.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(breakingNewsResponse ?: response)
            }
        }
        return Resource.Error(response.message())
    }

    private fun handleSearchResponse(response: Response<NewsResponse>) : Resource<NewsResponse> {
        if (response.isSuccessful) {
            response.body()?.let { response ->
                searchNewsPage++
                if (searchNewsResponse == null) {
                    searchNewsResponse = response
                } else {
                    val oldArticles = searchNewsResponse?.articles
                    val newArticles = response.articles
                    oldArticles?.addAll(newArticles)
                }
                return Resource.Success(searchNewsResponse ?: response)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveArticle(article: Article) = viewModelScope.launch {
        newsRepository.upsert(article)
    }

    fun getSavedNews() = newsRepository.getSavedNews()

    fun deleteArticle(article: Article) = viewModelScope.launch {
        newsRepository.deleteArticle(article)
    }
}