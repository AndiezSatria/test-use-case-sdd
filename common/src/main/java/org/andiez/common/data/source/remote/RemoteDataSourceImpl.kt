package org.andiez.common.data.source.remote

import com.github.ajalt.timberkt.e
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.common.data.source.remote.model.LineDataResponse
import org.andiez.common.data.source.remote.model.PieDataEntryResponse
import org.andiez.common.data.source.remote.model.PieDataResponse
import org.andiez.common.data.source.remote.service.AppService
import org.andiez.common.util.JsonHelper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class RemoteDataSourceImpl @Inject constructor(
    val service: AppService,
    private val jsonHelper: JsonHelper,
) : RemoteDataSource {
    override suspend fun getPieData(): Flow<ApiResponse<PieDataResponse>> =
        flow<ApiResponse<PieDataResponse>> {
            val data = jsonHelper.loadPieData()
            delay(2000)
            emit(ApiResponse.Success(data))
        }.catch {
            emit(ApiResponse.Error(it.message.toString()))
            e { it.toString() }
        }.flowOn(Dispatchers.IO)

    override suspend fun getLineData(): Flow<ApiResponse<LineDataResponse>> =
        flow<ApiResponse<LineDataResponse>> {
            val data = jsonHelper.loadLineData()
            delay(2000)
            emit(ApiResponse.Success(data))
        }.catch {
            emit(ApiResponse.Error(it.message.toString()))
            e { it.toString() }
        }.flowOn(Dispatchers.IO)

    override suspend fun getPieByName(chartName: String): Flow<ApiResponse<PieDataEntryResponse>> =
        flow {
            val data = jsonHelper.getPieDataFromName(chartName)
            delay(2000)
            data?.let {
                emit(ApiResponse.Success(data))
            } ?: run {
                emit(ApiResponse.Empty)
            }
        }.catch {
            emit(ApiResponse.Error(it.message.toString()))
            e { it.toString() }
        }.flowOn(Dispatchers.IO)

}