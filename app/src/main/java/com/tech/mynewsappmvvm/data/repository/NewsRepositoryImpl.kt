package com.tech.mynewsappmvvm.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.tech.mynewsappmvvm.data.remote.NewsApi
import com.tech.mynewsappmvvm.data.remote.NewsPagingSource
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class NewsRepositoryImpl(
    private val newsApi : NewsApi
) : NewsRepository{
    override fun getNews(sources: List<String>): Flow<PagingData<Article>> {
     return Pager(
            config = PagingConfig(pageSize = 10),
            pagingSourceFactory = {
                NewsPagingSource(
                    newsApi = newsApi,
                    sources = sources.joinToString(separator = ",")
                )
            }
        ).flow
    }

}