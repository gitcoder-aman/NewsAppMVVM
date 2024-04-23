package com.tech.mynewsappmvvm.domain.usecases.news

import com.tech.mynewsappmvvm.presentation.search.SearchNews

data class NewsUseCases(
    val getNews: GetNews,
    val searchNews : SearchNews
)
