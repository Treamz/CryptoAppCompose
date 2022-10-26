package com.example.cryptoappcompose.domain.use_case.get_coins

import com.example.cryptoappcompose.common.Resource
import com.example.cryptoappcompose.data.remote.dto.toCoin
import com.example.cryptoappcompose.domain.model.Coin
import com.example.cryptoappcompose.domain.repository.CoinRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCoinsUseCase @Inject constructor(
    private val repository: CoinRepository
) {

    operator fun invoke(): Flow<Resource<List<Coin>>> = flow {
        try {
            emit(Resource.Loading())
            val coins = repository.getCoins().map {
                it.toCoin()
            }
            emit(Resource.Success(coins))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage ?: "An unxpected error occured."))
        } catch (e: IOException) {
            println("HERE");

            println(e.localizedMessage);
            emit(Resource.Error("Couldn't reach server."))
        }
    }
}