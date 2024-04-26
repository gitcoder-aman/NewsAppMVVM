package com.tech.mynewsappmvvm.domain.usecases.news

import com.tech.mynewsappmvvm.data.local.NewsDao
import com.tech.mynewsappmvvm.domain.model.Article
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsDao: NewsDao
) {

     operator fun invoke() : Flow<List<Article>> {
        return newsDao.getArticles()
    }
}