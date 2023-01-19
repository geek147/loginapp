package com.codeonwheels.loginapp.di

import com.codeonwheels.loginapp.BuildConfig
import com.codeonwheels.loginapp.data.remote.SaltApi
import com.codeonwheels.loginapp.data.repository.SaltRepository
import com.codeonwheels.loginapp.data.repository.SaltRepositoryInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {

    @Singleton
    @Provides
    fun saltApiRepository(api: SaltApi) = SaltRepository(api) as SaltRepositoryInterface

    @Singleton
    @Provides
    fun injectBackendRetrofitApi() : SaltApi {

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(SaltApi::class.java)
    }
}