package org.andiez.common.data.source.local

import org.andiez.common.data.source.local.dao.AppDao
import org.andiez.core.functional.Either
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class LocalDataSourceImpl @Inject constructor(appDao: AppDao) : LocalDataSource {
}