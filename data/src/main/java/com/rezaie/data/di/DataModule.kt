package com.rezaie.data.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.rezaie.data.BuildConfig
import com.rezaie.data.api.local.db.convertors.StringConvertor
import com.rezaie.data.api.local.db.core.CharacterDataBase
import com.rezaie.data.api.local.db.service.CharacterDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.ConnectionPool
import okhttp3.Dispatcher
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideOkHttp(
        httpLoggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val timeOut = 30L
        val dispatcher = Dispatcher(Executors.newFixedThreadPool(20)).apply {
            maxRequests = 20
            maxRequestsPerHost = 20
        }
        return OkHttpClient.Builder().apply {
            dispatcher(dispatcher)
            connectionPool(ConnectionPool(100, timeOut, TimeUnit.SECONDS))
            readTimeout(timeOut, TimeUnit.SECONDS)
            writeTimeout(timeOut, TimeUnit.SECONDS)
            connectTimeout(timeOut, TimeUnit.SECONDS)
            addNetworkInterceptor(httpLoggingInterceptor)
        }.build()
    }


    @Provides
    @Singleton
    @OptIn(ExperimentalSerializationApi::class)
    fun provideJson() = Json {
        encodeDefaults = true
        ignoreUnknownKeys = true
        explicitNulls = false
    }

    @Provides
    @Singleton
    fun provideConverter(json: Json): Converter.Factory =
        json.asConverterFactory("application/json".toMediaType())


    @Provides
    @Singleton
    fun provideRetrofit(httpClient: OkHttpClient, converter: Converter.Factory): Retrofit =
        Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(converter)
            .baseUrl(BuildConfig.BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        convertor: StringConvertor
    ) =
        Room.databaseBuilder(
            context,
            CharacterDataBase::class.java,
            BuildConfig.DATABASE_NAME
        ).addTypeConverter(convertor)
            .fallbackToDestructiveMigration()
            .build()

    @Singleton
    @Provides
    fun provideCharactersSearchDao(characterDataBase: CharacterDataBase): CharacterDao {
        return characterDataBase.getCharacterDao()
    }
}