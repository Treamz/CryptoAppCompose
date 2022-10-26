package com.example.cryptoappcompose.domain.use_case.get_coin

import com.example.cryptoappcompose.common.Resource
import com.example.cryptoappcompose.data.remote.dto.toCoin
import com.example.cryptoappcompose.data.remote.dto.toCoinDetail
import com.example.cryptoappcompose.domain.model.Coin
import com.example.cryptoappcompose.domain.model.CoinDetail
import com.example.cryptoappcompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(coinId: String): Flow<Resource<CoinDetail>> = flow {
        try {
            emit(Resource.Loading())
            val coin = repository.getCoinById(coinId = coinId).toCoinDetail()
            emit(Resource.Success<CoinDetail>(coin))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unxpected error occured."))
        } catch (e: IOException) {
            emit(Resource.Error("Couldn't reach server."))
        }
    }
}