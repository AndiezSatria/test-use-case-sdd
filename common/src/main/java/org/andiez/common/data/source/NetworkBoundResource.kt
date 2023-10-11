package org.andiez.common.data.source

import com.github.ajalt.timberkt.d
import kotlinx.coroutines.flow.*
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either

abstract class NetworkBoundResource<ResultType, RequestType> {

    private var result: Flow<Either<Failure, ResultType>> = flow<Either<Failure, ResultType>> {
        val dbSource = loadFromDB().first()
        if (shouldFetch(dbSource)) {
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(loadFromDB().map { result ->
                        result?.let {
                            Either.Right(it)
                        } ?: run {
                            Either.Left(Failure.LocalDataNotFound)
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
            emitAll(loadFromDB().map { result ->
                result?.let {
                    Either.Right(it)
                } ?: run {
                    Either.Left(Failure.LocalDataNotFound)
                }
            })
        }
    }.catch {
        emit(Either.Left(Failure.ServerError(it.localizedMessage)))
    }

    protected open fun onFetchFailed() {}

    protected abstract fun loadFromDB(): Flow<ResultType?>

    protected abstract fun shouldFetch(data: ResultType?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<RequestType>>

    protected abstract suspend fun saveCallResult(data: RequestType)

    fun asFlow(): Flow<Either<Failure, ResultType>> = result
}