package org.andiez.common.data.source.remote

import org.andiez.common.data.source.remote.service.AppService
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class RemoteDataSourceImpl @Inject constructor(val service: AppService): RemoteDataSource {

}