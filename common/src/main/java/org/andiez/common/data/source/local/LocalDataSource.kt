package org.andiez.common.data.source.local

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.model.User

/**
 * Created by AndiezSatria on 17/04/2023.
 */
interface LocalDataSource {
    suspend fun insertTransaction(transaction: Transaction)
    fun getAllTransaction(): Flow<List<Transaction>>
    suspend fun insertUser(user: User)
    fun getUser(userId: Int): Flow<User>

    suspend fun updateBalance(id: Int, transactionAmount: Long)
}