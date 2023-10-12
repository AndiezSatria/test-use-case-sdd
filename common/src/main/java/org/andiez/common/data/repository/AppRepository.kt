package org.andiez.common.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import org.andiez.common.data.source.NetworkOnlyResource
import org.andiez.common.data.source.local.LocalDataSource
import org.andiez.common.data.source.remote.RemoteDataSource
import org.andiez.common.data.source.remote.model.ApiResponse
import org.andiez.common.data.source.remote.model.LineDataResponse
import org.andiez.common.data.source.remote.model.PieDataEntryResponse
import org.andiez.common.data.source.remote.model.PieDataResponse
import org.andiez.common.data.source.remote.model.entryToDomain
import org.andiez.common.data.source.remote.model.toDomain
import org.andiez.common.domain.model.LineData
import org.andiez.common.domain.model.PieDataDomain
import org.andiez.common.domain.model.PieDataEntry
import org.andiez.common.domain.repository.IAppRepository
import org.andiez.common.util.AppExecutors
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either
import org.andiez.core.network.NetworkChecker
import javax.inject.Inject

/**
 * Created by AndiezSatria on 17/04/2023.
 */


class AppRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors,
    private val networkChecker: NetworkChecker,
) : IAppRepository {
    override fun getPieData(): Flow<Either<Failure, PieDataDomain>> =
        object : NetworkOnlyResource<PieDataDomain, PieDataResponse>(networkChecker) {
            override fun loadFromNetwork(data: PieDataResponse): Flow<PieDataDomain?> {
                return flowOf(data.toDomain())
            }

            override suspend fun createCall(): Flow<ApiResponse<PieDataResponse>> {
                return remoteDataSource.getPieData()
            }

        }.asFlow()

    override fun getLineData(): Flow<Either<Failure, LineData>> =
        object : NetworkOnlyResource<LineData, LineDataResponse>(networkChecker) {
            override fun loadFromNetwork(data: LineDataResponse): Flow<LineData?> {
                return flowOf(data.toDomain())
            }

            override suspend fun createCall(): Flow<ApiResponse<LineDataResponse>> {
                return remoteDataSource.getLineData()
            }

        }.asFlow()

    override fun getPieByName(chartName: String): Flow<Either<Failure, PieDataEntry>> =
        object : NetworkOnlyResource<PieDataEntry, PieDataEntryResponse>(networkChecker) {
            override fun loadFromNetwork(data: PieDataEntryResponse): Flow<PieDataEntry?> {
                return flowOf(entryToDomain(data))
            }

            override suspend fun createCall(): Flow<ApiResponse<PieDataEntryResponse>> {
                return remoteDataSource.getPieByName(chartName)
            }

        }.asFlow()
}