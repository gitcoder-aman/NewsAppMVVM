package com.tech.mynewsappmvvm.domain.repository

import androidx.paging.PagingData
import com.tech.mynewsappmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow

interface NewsRepository {

    //       pagingData is basically a technic that enable that use fetch data from server
    fun getNews(sources : List<String>) : Flow<PagingData<Article>>
    fun searchNews(searchQuery:String,sources : List<String>) : Flow<PagingData<Article>>

    suspend fun upsertArticle(article: Article)
    suspend fun deleteArticle(article: Article)

    fun selectArticles() : Flow<List<Article>>

    suspend fun selectArticle(url : String) : Article?
}