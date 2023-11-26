package com.rbs.newsapp.ui.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rbs.newsapp.data.repository.MainRepository
import kotlinx.coroutines.launch

class MainViewModel(private val repository: MainRepository) : ViewModel() {
    private val listCategory = MutableLiveData<List<String>>()
    var category: LiveData<List<String>> = listCategory

    fun getData(data: Array<String>) {
        viewModelScope.launch {
            listCategory.postValue(repository.collectData(data))
        }
    }
}