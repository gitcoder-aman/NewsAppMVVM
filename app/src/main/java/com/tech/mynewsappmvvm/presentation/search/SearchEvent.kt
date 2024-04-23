package com.tech.mynewsappmvvm.presentation.search

sealed class SearchEvent {

    data class UpdateSearchQuery(val searchQuery: String) : SearchEvent()
    object  SearchNews : SearchEvent()

}