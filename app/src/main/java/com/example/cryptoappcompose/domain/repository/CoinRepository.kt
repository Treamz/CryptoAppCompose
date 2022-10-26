package com.example.cryptoappcompose.domain.repository

import com.example.cryptoappcompose.data.remote.dto.CoinDetailDto
import com.example.cryptoappcompose.data.remote.dto.CoinDto

interface CoinRepository {

    suspend fun getCoins(): List<CoinDto>

    suspend fun getCoinById(coinId: String): CoinDetailDto
}