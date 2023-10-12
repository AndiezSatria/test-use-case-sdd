package org.andiez.common.data.source.local

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.Promo

/**
 * Created by AndiezSatria on 17/04/2023.
 */
interface LocalDataSource {
    fun getPromos(): Flow<List<Promo>>
    fun getPromo(id: Int): Flow<Promo>
    suspend fun insertAllPromos(list: List<Promo>)
}