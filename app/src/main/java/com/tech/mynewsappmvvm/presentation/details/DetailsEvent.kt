package com.tech.mynewsappmvvm.presentation.details

import com.tech.mynewsappmvvm.domain.model.Article

sealed class DetailsEvent {
    data class UpsertDeleteArticle(val article: Article) : DetailsEvent()
    object RemoveSideEffect : DetailsEvent()
}