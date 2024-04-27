package com.tech.mynewsappmvvm.domain.usecases.news

import com.tech.mynewsappmvvm.data.local.NewsDao
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow

class SelectArticles(
    private val newsRepository: NewsRepository
) {

     operator fun invoke() : Flow<List<Article>> {
        return newsRepository.selectArticles()
    }
}