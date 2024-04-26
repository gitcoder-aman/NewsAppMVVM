package com.tech.mynewsappmvvm.di

import android.app.Application
import androidx.room.Room
import com.tech.mynewsappmvvm.data.local.NewsDao
import com.tech.mynewsappmvvm.data.local.NewsDatabase
import com.tech.mynewsappmvvm.data.local.NewsTypeConvertor
import com.tech.mynewsappmvvm.data.manager.LocalUserMangerImpl
import com.tech.mynewsappmvvm.data.remote.dto.NewsApi
import com.tech.mynewsappmvvm.data.repository.NewsRepositoryImpl
import com.tech.mynewsappmvvm.domain.manager.LocalUserManger
import com.tech.mynewsappmvvm.domain.repository.NewsRepository
import com.tech.mynewsappmvvm.domain.usecases.app_entry.AppEntryUseCases
import com.tech.mynewsappmvvm.domain.usecases.app_entry.ReadAppEntry
import com.tech.mynewsappmvvm.domain.usecases.app_entry.SaveAppEntry
import com.tech.mynewsappmvvm.domain.usecases.news.DeleteArticle
import com.tech.mynewsappmvvm.domain.usecases.news.GetNews
import com.tech.mynewsappmvvm.domain.usecases.news.NewsUseCases
import com.tech.mynewsappmvvm.domain.usecases.news.SelectArticles
import com.tech.mynewsappmvvm.domain.usecases.news.UpsertArticle
import com.tech.mynewsappmvvm.presentation.search.SearchNews
import com.tech.mynewsappmvvm.util.Constants.BASE_URL
import com.tech.mynewsappmvvm.util.Constants.NEWS_DATABASE_NAME
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
    fun provideNewsApi(): NewsApi {
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
    ): NewsRepository = NewsRepositoryImpl(newsApi)

    @Provides
    @Singleton
    fun provideNewsUseCases(
        newsRepository: NewsRepository,
        newsDao: NewsDao
    ): NewsUseCases {
        return NewsUseCases(
            getNews = GetNews(newsRepository),
            searchNews = SearchNews(newsRepository),
            upsertArticle = UpsertArticle(newsDao),
            deleteArticle = DeleteArticle(newsDao),
            selectArticle = SelectArticles(newsDao)
        )
    }

    @Provides
    @Singleton
    fun provideNewsDatabase(
        application: Application
    ): NewsDatabase {
        return Room.databaseBuilder(
            context = application,
            klass = NewsDatabase::class.java,
            name = NEWS_DATABASE_NAME
        ).addTypeConverter(NewsTypeConvertor())
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideNewsDao(
        newsDatabase: NewsDatabase
    ) : NewsDao = newsDatabase.newsDao
}