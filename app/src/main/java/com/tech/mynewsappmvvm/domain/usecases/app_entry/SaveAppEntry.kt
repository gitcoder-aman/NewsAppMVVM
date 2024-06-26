package com.tech.mynewsappmvvm.domain.usecases.app_entry

import com.tech.mynewsappmvvm.domain.manager.LocalUserManger

class SaveAppEntry(
    private val localUserManager : LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}