package com.tech.mynewsappmvvm.domain.usecases.app_entry

import com.tech.mynewsappmvvm.domain.manager.LocalUserManger
import kotlinx.coroutines.flow.Flow

class ReadAppEntry(
    private val localUserManager: LocalUserManger
) {
     operator fun invoke() : Flow<Boolean> {
        return localUserManager.readAppEntry()
    }
}