package com.example.cryptoappcompose.presentation.coin_detail

import com.example.cryptoappcompose.domain.model.CoinDetail

data class CoinDetailState(
    val isLoading: Boolean = false,
    val coin: CoinDetail?= null,
    val error: String = ""
)