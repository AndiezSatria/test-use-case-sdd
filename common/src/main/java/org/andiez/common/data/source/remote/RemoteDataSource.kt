package org.andiez.common.data.source.remote

import kotlinx.coroutines.flow.Flow
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.common.data.source.remote.model.FormatResponse
import org.andiez.common.data.source.remote.model.ImageFormatResponse
import org.andiez.common.data.source.remote.model.ImageResponse
import org.andiez.common.data.source.remote.model.PromoResponse

/**
 * Created by AndiezSatria on 17/04/2023.
 */

interface RemoteDataSource {
    suspend fun getPromos(): Flow<ApiResponse<List<PromoResponse<ImageResponse<FormatResponse<ImageFormatResponse>>>>>>
}