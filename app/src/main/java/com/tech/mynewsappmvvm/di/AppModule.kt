package com.tech.mynewsappmvvm.di

import android.app.Application
import com.tech.mynewsappmvvm.data.manager.LocalUserMangerImpl
import com.tech.mynewsappmvvm.data.remote.NewsApi
import com.tech.mynewsappmvvm.data.repository.NewsRepositoryImpl
import com.tech.mynewsappmvvm.domain.manager.LocalUserManger
import com.tech.mynewsappmvvm.domain.repository.NewsRepository
import com.tech.mynewsappmvvm.domain.usecases.app_entry.AppEntryUseCases
import com.tech.mynewsappmvvm.domain.usecases.app_entry.ReadAppEntry
import com.tech.mynewsappmvvm.domain.usecases.app_entry.SaveAppEntry
import com.tech.mynewsappmvvm.domain.usecases.news.GetNews
import com.tech.mynewsappmvvm.domain.usecases.news.NewsUseCases
import com.tech.mynewsappmvvm.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideLocalUserManager(
        application: Application
    ): LocalUserManger = LocalUserMangerImpl(application)

    @Provides
    @Singleton
    fun provideAppEntryUseCases(
        localUserManger: LocalUserManger
    ) = AppEntryUseCases(
        readAppEntry = ReadAppEntry(localUserManger),
        saveAppEntry = SaveAppEntry(localUserManger)
    )

    @Provides
    @Singleton
    fun provideNewsApi() : NewsApi{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsApi::class.java)
    }
    @Provides
    @Singleton
    fun provideNewsRepository(
        newsApi: NewsApi
    ) : NewsRepository = NewsRepositoryImpl(newsApi)
    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository
    ) : NewsUseCases{
        return NewsUseCases(
            getNews = GetNews(newsRepository)
        )
    }
}