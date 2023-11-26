package com.rbs.newsapp.di

import com.rbs.newsapp.data.network.ApiConfig
import com.rbs.newsapp.data.repository.MainRepository
import com.rbs.newsapp.data.repository.ArticleRepository
import com.rbs.newsapp.data.repository.SearchRepository
import com.rbs.newsapp.data.repository.SourceRepository

object Injection {
    fun mainRepository(): MainRepository {
        return MainRepository()
    }

    fun articleRepository(): ArticleRepository {
        val apiService = ApiConfig.getService()
        return ArticleRepository(apiService)
    }

    fun sourceRepository(): SourceRepository {
        val apiService = ApiConfig.getService()
        return SourceRepository(apiService)
    }

    fun searchRepository(): SearchRepository {
        val apiService = ApiConfig.getService()
        return SearchRepository(apiService)
    }
}