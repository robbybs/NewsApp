package com.rbs.newsapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbs.newsapp.di.Injection
import com.rbs.newsapp.ui.article_screen.ArticleViewModel
import com.rbs.newsapp.ui.source_screen.SourceNewsViewModel

class VMFactoryWithData(private val data: String) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SourceNewsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SourceNewsViewModel(data, Injection.sourceRepository()) as T
        } else if (modelClass.isAssignableFrom(ArticleViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ArticleViewModel(data, Injection.articleRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}