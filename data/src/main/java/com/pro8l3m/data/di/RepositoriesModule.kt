package com.pro8l3m.data.di

import com.pro8l3m.data.repositories.DefaultMemesRepository
import com.pro8l3m.domain.repositories.MemesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface RepositoriesModule {

    @Binds
    fun bindMemesRepository(repository: DefaultMemesRepository): MemesRepository
}