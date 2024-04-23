package com.tech.mynewsappmvvm.presentation.search

import androidx.paging.PagingData
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SearchNews(
    private val newRepository : NewsRepository
){
    operator fun invoke(searchQuery : String,sources : List<String>):Flow<PagingData<Article>>{
        return newRepository.searchNews(
            sources = sources,
            searchQuery = searchQuery
        )
    }
}