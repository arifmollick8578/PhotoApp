package com.mollick.photoapp.data.di

import com.mollick.photoapp.common.Constants.BASE_URL
import com.mollick.photoapp.data.remote.JsonPlaceholderApi
import com.mollick.photoapp.data.repository.UserRepositoryImpl
import com.mollick.photoapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/** Module object to inject value within whole Application using Hilt. */
@Module
@InstallIn(SingletonComponent::class)
object Module {

    @Singleton
    @Provides
    fun provideJsonPlaceholderApi(): JsonPlaceholderApi {
        return Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JsonPlaceholderApi::class.java)
    }

    @Singleton
    @Provides
    fun getUserRepository(api: JsonPlaceholderApi): UserRepository {
        return UserRepositoryImpl(api)
    }
}
