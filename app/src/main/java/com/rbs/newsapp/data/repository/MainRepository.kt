package com.rbs.newsapp.data.repository

class MainRepository {
    fun collectData(category: Array<String>): List<String> = category.toList()
}