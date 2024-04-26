package com.tech.mynewsappmvvm.presentation.bookmark

import com.tech.mynewsappmvvm.domain.model.Article

data class BookmarkState(
    val articles : List<Article> = emptyList()
)
