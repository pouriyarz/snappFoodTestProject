package com.rezaie.data.di

import com.rezaie.data.api.remote.CharacterApiRemoteService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RemoteServicesModule {

    @Provides
    @Singleton
    fun provideSupervisorApiRemoteService(retrofit: Retrofit): CharacterApiRemoteService {
        return retrofit.create(CharacterApiRemoteService::class.java)
    }
}