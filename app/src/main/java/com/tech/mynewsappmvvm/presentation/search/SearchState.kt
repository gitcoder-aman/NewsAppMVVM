package com.tech.mynewsappmvvm.presentation.search

import androidx.paging.PagingData
import com.tech.mynewsappmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class SearchState(
    val searchQuery : String = "",
    val articles : Flow<PagingData<Article>>?= null
)