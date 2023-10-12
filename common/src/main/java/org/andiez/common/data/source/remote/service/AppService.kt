package org.andiez.common.data.source.remote.service

import org.andiez.common.data.source.remote.model.FormatResponse
import org.andiez.common.data.source.remote.model.ImageFormatResponse
import org.andiez.common.data.source.remote.model.ImageResponse
import org.andiez.common.data.source.remote.model.PromoResponse
import retrofit2.http.GET

/**
 * Created by AndiezSatria on 17/04/2023.
 */

interface AppService {

    @GET("promos")
    suspend fun getPromos(): List<PromoResponse<ImageResponse<FormatResponse<ImageFormatResponse>>>>
}