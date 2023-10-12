package org.andiez.common.data.source.remote

import kotlinx.coroutines.flow.Flow
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.common.data.source.remote.model.LineDataResponse
import org.andiez.common.data.source.remote.model.PieDataEntryResponse
import org.andiez.common.data.source.remote.model.PieDataResponse

/**
 * Created by AndiezSatria on 17/04/2023.
 */

interface RemoteDataSource {
    suspend fun getPieData(): Flow<ApiResponse<PieDataResponse>>

    suspend fun getLineData(): Flow<ApiResponse<LineDataResponse>>

    suspend fun getPieByName(chartName: String): Flow<ApiResponse<PieDataEntryResponse>>
}