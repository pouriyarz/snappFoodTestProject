package com.rezaie.data.di

import com.rezaie.data.datasource.LocalCharactersDataSource
import com.rezaie.data.datasource.LocalCharactersDataSourceImpl
import com.rezaie.data.datasource.RemoteCharactersDataSource
import com.rezaie.data.datasource.RemoteCharactersDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {
    @Binds
    @Singleton
    abstract fun bindLocalCharactersDataSource(
        dataSourceImpl:
        LocalCharactersDataSourceImpl
    ): LocalCharactersDataSource

    @Binds
    @Singleton
    abstract fun bindRemoteCharactersDataSource(
        dataSource: RemoteCharactersDataSourceImpl
    ): RemoteCharactersDataSource
}