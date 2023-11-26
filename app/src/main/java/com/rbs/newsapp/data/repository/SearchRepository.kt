package com.rbs.newsapp.data.repository

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.rbs.newsapp.data.model.ArticlesItem
import com.rbs.newsapp.data.network.ApiService
import com.rbs.newsapp.utils.paging.ArticlesPagingSource

class SearchRepository(private val apiService: ApiService) {
    fun getArticles(query: String): LiveData<PagingData<ArticlesItem>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10
            ),
            pagingSourceFactory = {
                ArticlesPagingSource(query, apiService)
            }
        ).liveData
    }
}