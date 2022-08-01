package com.dev_marinov.nbadata.di

import com.dev_marinov.nbadata.data.remote.NbaRepository
import com.dev_marinov.nbadata.domain.INbaRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNbaRepository(nbaRepository: NbaRepository) :INbaRepository
}