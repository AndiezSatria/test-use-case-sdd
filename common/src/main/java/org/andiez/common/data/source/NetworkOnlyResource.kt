package org.andiez.common.data.source

import com.github.ajalt.timberkt.d
import kotlinx.coroutines.flow.*
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either
import org.andiez.core.network.NetworkChecker

abstract class NetworkOnlyResource<ResultType, RequestType>(private val networkChecker: NetworkChecker) {

    private var result: Flow<Either<Failure, ResultType>> = flow {
        if (networkChecker.isNetworkConnected()) {
            d { "connection : connect to internet" }
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    emitAll(loadFromNetwork(apiResponse.data).map { result ->
                        result?.let {
                            Either.Right(it)
                        } ?: run {
                            Either.Left(Failure.NetworkException)
                        }
                    })
                }

                is ApiResponse.Empty -> {
                    emit(Either.Left(Failure.Nothing))
                }

                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(Either.Left(Failure.ServerError(apiResponse.errorMessage)))
                }
            }
        } else {
            d { "connection : disconnect" }
            // not connected
            emit(Either.Left(Failure.NetworkException))
        }
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromNetwork(data: RequestType): Flow<ResultType?>

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    fun asFlow(): Flow<Either<Failure, ResultType>> = result
}