package com.tech.mynewsappmvvm.data.remote.dto

import com.tech.mynewsappmvvm.data.remote.dto.NewsResponse
import com.tech.mynewsappmvvm.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApi {

    @GET("everything")
    suspend fun getNews(
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String = API_KEY
    ) : NewsResponse

    @GET("everything")
    suspend fun searchNews(
        @Query("q") searchQuery : String,
        @Query("page") page : Int,
        @Query("sources") sources : String,
        @Query("apiKey") apiKey : String = API_KEY,
    ):NewsResponse
}