package com.rbs.newsapp.ui.source_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbs.newsapp.data.model.SourcesItem
import com.rbs.newsapp.data.repository.SourceRepository
import kotlinx.coroutines.launch

class SourceNewsViewModel(private val category: String, private val repository: SourceRepository) : ViewModel() {
    private val _sources = MutableLiveData<List<SourcesItem>>()
    var sources: LiveData<List<SourcesItem>> = _sources

    fun getSources() {
        viewModelScope.launch {
            _sources.postValue(repository.getSources(category))
        }
    }
}