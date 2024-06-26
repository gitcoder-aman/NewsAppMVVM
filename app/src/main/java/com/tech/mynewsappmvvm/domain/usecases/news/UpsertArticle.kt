package com.tech.mynewsappmvvm.domain.usecases.news

import com.tech.mynewsappmvvm.data.local.NewsDao
import com.tech.mynewsappmvvm.domain.model.Article
import com.tech.mynewsappmvvm.domain.repository.NewsRepository

class UpsertArticle(
    private val newsRepository: NewsRepository
) {

    suspend operator fun invoke(article: Article){
        newsRepository.upsertArticle(article)
    }
}