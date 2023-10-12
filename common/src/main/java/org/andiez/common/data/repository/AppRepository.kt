package org.andiez.common.data.repository

import com.github.ajalt.timberkt.d
import kotlinx.coroutines.flow.Flow
import org.andiez.common.data.source.NetworkBoundResource
import org.andiez.common.data.source.local.LocalDataSource
import org.andiez.common.data.source.remote.RemoteDataSource
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.common.data.source.remote.model.FormatResponse
import org.andiez.common.data.source.remote.model.ImageFormatResponse
import org.andiez.common.data.source.remote.model.ImageResponse
import org.andiez.common.data.source.remote.model.PromoResponse
import org.andiez.common.data.source.remote.model.toDomains
import org.andiez.common.domain.model.Promo
import org.andiez.common.domain.repository.IAppRepository
import org.andiez.common.util.AppExecutors
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either
import javax.inject.Inject

/**
 * Created by AndiezSatria on 17/04/2023.
 */


class AppRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors,
) : IAppRepository {
    override fun getPromos(): Flow<Either<Failure, List<Promo>>> =
        object : NetworkBoundResource<List<Promo>, List<PromoResponse<ImageResponse<FormatResponse<ImageFormatResponse>>>>>() {
            override fun loadFromDB(): Flow<List<Promo>?> {
                return localDataSource.getPromos()
            }

            override suspend fun createCall(): Flow<ApiResponse<List<PromoResponse<ImageResponse<FormatResponse<ImageFormatResponse>>>>>> {
                d { "Get Promos" }
                return remoteDataSource.getPromos()
            }

            override suspend fun saveCallResult(data: List<PromoResponse<ImageResponse<FormatResponse<ImageFormatResponse>>>>) {
                val domains = data.toDomains()
                localDataSource.insertAllPromos(domains)
            }

            override fun shouldFetch(data: List<Promo>?): Boolean = data.isNullOrEmpty()

        }.asFlow()

    override fun getPromo(id: Int): Flow<Promo> {
        return localDataSource.getPromo(id)
    }

}