package com.rbs.newsapp.data.repository

import com.rbs.newsapp.BuildConfig
import com.rbs.newsapp.data.model.SourcesItem
import com.rbs.newsapp.data.network.ApiService

class SourceRepository(private val apiService: ApiService) {
    suspend fun getSources(category: String): List<SourcesItem> =
        apiService.getSourceNews(category, BuildConfig.KEY).sources
}