package com.tech.mynewsappmvvm.data.remote.dto

import com.tech.mynewsappmvvm.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)