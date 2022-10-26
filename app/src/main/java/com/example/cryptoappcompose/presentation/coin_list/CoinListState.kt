package com.example.cryptoappcompose.presentation.coin_list

import com.example.cryptoappcompose.domain.model.Coin

data class CoinListState(
    val isLoading: Boolean = false,
    val coins: List<Coin> = emptyList(),
    val error: String = ""
)