package org.andiez.common.data.repository

import org.andiez.common.data.source.local.LocalDataSource
import org.andiez.common.data.source.remote.RemoteDataSource
import org.andiez.common.domain.repository.IAppRepository
import org.andiez.common.util.AppExecutors
import javax.inject.Inject

/**
 * Created by AndiezSatria on 17/04/2023.
 */


class AppRepository @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
    private val appExecutors: AppExecutors,
): IAppRepository {
}