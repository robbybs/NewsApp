package com.rbs.newsapp.ui.article_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbs.newsapp.data.model.ArticlesItem
import com.rbs.newsapp.data.repository.ArticleRepository
import kotlinx.coroutines.launch

class ArticleViewModel(private val sources: String, private val repository: ArticleRepository) : ViewModel() {
    private val _articles = MutableLiveData<List<ArticlesItem>>()
    var articles: LiveData<List<ArticlesItem>> = _articles

    fun getArticles() {
        viewModelScope.launch {
            _articles.postValue(repository.getArticles(sources))
        }
    }
}