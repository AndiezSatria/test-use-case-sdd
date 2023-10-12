package org.andiez.common.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.andiez.common.data.source.local.entity.PromoEntity

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Dao
interface AppDao {
    @Query("SELECT * FROM PromoTable")
    fun getPromos(): Flow<List<PromoEntity>>

    @Query("SELECT * FROM PromoTable WHERE id = :id")
    fun getPromo(id: Int): Flow<PromoEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllPromo(list: List<PromoEntity>)
}