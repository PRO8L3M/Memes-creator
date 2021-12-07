package com.pro8l3m.memescreator.di

import android.content.Context
import com.pro8l3m.data.datastore.ApplicationDataStore
import com.pro8l3m.data.datastore.IDataStore
import com.pro8l3m.data.di.EndpointsModule
import com.pro8l3m.data.di.RepositoriesModule
import com.pro8l3m.data.di.RetrofitModule
import com.pro8l3m.data.di.ServicesModule
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module(includes = [RepositoriesModule::class, ServicesModule::class, EndpointsModule::class, RetrofitModule::class])
interface DataModule {

    companion object {

        @Provides
        fun provideDataStore(@ApplicationContext appContext: Context): IDataStore =
            ApplicationDataStore(appContext)
    }
}