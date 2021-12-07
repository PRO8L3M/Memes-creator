package com.pro8l3m.data.di

import com.pro8l3m.data.remote.endpoints.MemesEndpoints
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
object EndpointsModule {

    @Provides
    fun provideMemesEndpoints(retrofit: Retrofit): MemesEndpoints {
        return retrofit.create(MemesEndpoints::class.java)
    }
}