package com.rbs.newsapp.data.model

import com.google.gson.annotations.SerializedName

data class SourceItem(
    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("name")
    val name: String? = null
)
