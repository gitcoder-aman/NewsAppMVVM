package com.tech.mynewsappmvvm.domain.usercases

import com.tech.mynewsappmvvm.domain.manager.LocalUserManger

class SaveAppEntry(
    private val localUserManager : LocalUserManger
) {
    suspend operator fun invoke(){
        localUserManager.saveAppEntry()
    }
}