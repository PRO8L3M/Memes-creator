package com.pro8l3m.data.di

import com.pro8l3m.data.remote.services.MemesService
import com.pro8l3m.data.remote.services.api.DefaultMemesService
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface ServicesModule {

    @Binds
    fun bindMemesService(service: DefaultMemesService): MemesService
}