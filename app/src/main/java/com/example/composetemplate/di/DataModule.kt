package com.example.composetemplate.di

import android.content.Context
import com.example.data.datastore.ApplicationDataStore
import com.example.data.datastore.IDataStore
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
interface DataModule {

    companion object {
        @Provides
        fun provideDataStore(@ApplicationContext appContext: Context): IDataStore = ApplicationDataStore(appContext)
    }
}