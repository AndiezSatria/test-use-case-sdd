package org.andiez.common.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import org.andiez.common.data.source.local.entity.TransactionEntity
import org.andiez.common.data.source.local.entity.UserEntity

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: UserEntity)

    @Query("UPDATE UserTable SET balance = :transactionAmount WHERE id = :id")
    suspend fun updateUserBalance(id: Int, transactionAmount: Long)

    @Query("SELECT * FROM UserTable WHERE id = :userId")
    fun getUser(userId: Int): Flow<UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransaction(transaction: TransactionEntity)

    @Query("SELECT * FROM TransactionTable")
    fun getAllTransaction(): Flow<List<TransactionEntity>>
}