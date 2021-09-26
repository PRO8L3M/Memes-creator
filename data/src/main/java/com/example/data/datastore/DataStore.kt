package com.example.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class ApplicationDataStore @Inject constructor(
    private val appContext: Context
) : IDataStore {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "pref")

    override suspend fun saveThemeMode(mode: Int) {
        appContext.dataStore.edit { settings ->
            settings[THEME_MODE] = mode
        }
    }

    override fun getThemeMode(): Flow<Int> = appContext.dataStore.data
        .map { preferences ->
            preferences[THEME_MODE] ?: -1
        }

    companion object {
        private val THEME_MODE = intPreferencesKey("theme_mode")
    }
}