package com.tech.mynewsappmvvm.presentation.details

sealed class DetailsEvent {
    object SaveArticle : DetailsEvent()
}