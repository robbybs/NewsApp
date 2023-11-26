package com.rbs.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class ArticlesItem(
    @field:SerializedName("source")
    val source: SourceItem? = null,

    @field:SerializedName("author")
    val author: String? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("url")
    val url: String? = null,

    @field:SerializedName("urlToImage")
    val urlToImage: String? = null,

    @field:SerializedName("publishedAt")
    val publishedAt: String? = null
)