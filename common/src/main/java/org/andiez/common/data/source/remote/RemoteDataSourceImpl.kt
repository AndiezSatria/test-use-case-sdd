package org.andiez.common.data.source.remote

import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.common.data.source.remote.model.FormatResponse
import org.andiez.common.data.source.remote.model.ImageFormatResponse
import org.andiez.common.data.source.remote.model.ImageResponse
import org.andiez.common.data.source.remote.model.PromoResponse
import org.andiez.common.data.source.remote.service.AppService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    private val service: AppService,
) : RemoteDataSource {
    override suspend fun getPromos(): Flow<ApiResponse<List<PromoResponse<ImageResponse<FormatResponse<ImageFormatResponse>>>>>> = flow {
        try {
            val response = service.getPromos()
            if (response.isEmpty()) {
                emit(ApiResponse.Empty)
            } else {
                d { "response : ${response[0].img?.url}" }
                emit(ApiResponse.Success(response))
            }
        } catch (e: Exception) {
            val message = e.message.toString()
            e.printStackTrace()
            emit(ApiResponse.Error(message))
        }
    }.catch {
        e { it.message.toString() }
        emit(ApiResponse.Error("Error Server : ${it.message}"))
    }.flowOn(Dispatchers.IO)

}