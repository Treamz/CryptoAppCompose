package com.example.cryptoappcompose.di

import com.example.cryptoappcompose.common.Constants
import com.example.cryptoappcompose.data.remote.CoinPaprikaApi
import com.example.cryptoappcompose.data.repository.CoinRepositoryImpl
import com.example.cryptoappcompose.domain.repository.CoinRepository
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providerPaprikaApi(): CoinPaprikaApi {
        return Retrofit.Builder().baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(CoinPaprikaApi::class.java)
    }

    @Provides
    @Singleton
    fun providerCoinRepository(api: CoinPaprikaApi) : CoinRepository {
        return CoinRepositoryImpl(api)
    }

}