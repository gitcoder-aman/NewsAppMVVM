package com.tech.mynewsappmvvm.data.manager

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.tech.mynewsappmvvm.domain.manager.LocalUserManger
import com.tech.mynewsappmvvm.util.Constants
import com.tech.mynewsappmvvm.util.Constants.USER_SETTINGS
import dagger.hilt.android.HiltAndroidApp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LocalUserMangerImpl(
    private val context : Context
) : LocalUserManger{
    override suspend fun saveAppEntry() {
        context.dataStore.edit { settings->
            settings[PreferenceKeys.APP_ENTRY] = true
        }
    }
    override fun readAppEntry(): Flow<Boolean> {
        return context.dataStore.data.map {preferences->
            preferences[PreferenceKeys.APP_ENTRY] ?: false
        }
    }
}
private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = USER_SETTINGS)

private object PreferenceKeys{
    val APP_ENTRY = booleanPreferencesKey(name = Constants.APP_ENTRY)
}