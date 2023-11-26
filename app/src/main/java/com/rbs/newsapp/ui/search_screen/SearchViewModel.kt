package com.rbs.newsapp.ui.search_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rbs.newsapp.data.model.ArticlesItem
import com.rbs.newsapp.data.repository.SearchRepository

class SearchViewModel(private val repository: SearchRepository) : ViewModel() {
    fun search(query: String): LiveData<PagingData<ArticlesItem>> =
        repository.getArticles(query).cachedIn(viewModelScope)
}