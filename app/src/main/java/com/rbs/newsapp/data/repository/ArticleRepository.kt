package com.rbs.newsapp.data.repository

import com.rbs.newsapp.BuildConfig
import com.rbs.newsapp.data.model.ArticlesItem
import com.rbs.newsapp.data.network.ApiService

class ArticleRepository(private val apiService: ApiService) {
    suspend fun getArticles(sources: String): List<ArticlesItem> =
        apiService.getArticles(sources, BuildConfig.KEY).articles
}