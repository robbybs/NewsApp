package com.rbs.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @field:SerializedName("status")
    val status: String,

    @field:SerializedName("totalResults")
    val totalResults: String,

    @field:SerializedName("articles")
    val articles: List<ArticlesItem>
)