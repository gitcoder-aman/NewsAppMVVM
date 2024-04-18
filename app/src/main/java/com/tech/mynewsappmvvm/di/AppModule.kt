package com.tech.mynewsappmvvm.di

import android.app.Application
import com.tech.mynewsappmvvm.data.manager.LocalUserMangerImpl
import com.tech.mynewsappmvvm.domain.manager.LocalUserManger
import com.tech.mynewsappmvvm.domain.usecases.AppEntryUseCases
import com.tech.mynewsappmvvm.domain.usecases.ReadAppEntry
import com.tech.mynewsappmvvm.domain.usecases.SaveAppEntry
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
}