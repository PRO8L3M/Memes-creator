package com.pro8l3m.data.datastore

import kotlinx.coroutines.flow.Flow

interface IDataStore {

    suspend fun saveThemeMode(mode: Int)
    fun getThemeMode(): Flow<Int>
}