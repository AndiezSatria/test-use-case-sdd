package org.andiez.common.data.source.local

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.andiez.common.data.source.local.dao.AppDao
import org.andiez.common.data.source.local.entity.toDomain
import org.andiez.common.data.source.local.entity.toDomains
import org.andiez.common.domain.model.Promo
import org.andiez.common.domain.model.toEntities
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class LocalDataSourceImpl @Inject constructor(private val appDao: AppDao) : LocalDataSource {
    override fun getPromos(): Flow<List<Promo>> {
        return appDao.getPromos().map { list ->
            list.toDomains()
        }
    }

    override fun getPromo(id: Int): Flow<Promo> {
        return appDao.getPromo(id).map { it.toDomain() }
    }

    override suspend fun insertAllPromos(list: List<Promo>) {
        appDao.insertAllPromo(list.toEntities())
    }
}