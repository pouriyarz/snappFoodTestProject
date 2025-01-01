package com.rezaie.data.di

import com.rezaie.data.repository.CharactersRepositoryImpl
import com.rezaie.domain.domainCharacteres.repository.CharacterRepository
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
    abstract fun bindCharactersRepository(
        repository: CharactersRepositoryImpl
    ): CharacterRepository
}