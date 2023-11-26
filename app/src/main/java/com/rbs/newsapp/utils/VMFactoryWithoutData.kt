package com.rbs.newsapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.rbs.newsapp.di.Injection
import com.rbs.newsapp.ui.home_screen.MainViewModel
import com.rbs.newsapp.ui.search_screen.SearchViewModel

class VMFactoryWithoutData : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MainViewModel(Injection.mainRepository()) as T
        } else if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchViewModel(Injection.searchRepository()) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}