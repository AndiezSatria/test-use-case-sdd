package org.andiez.common.domain.repository

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.model.User

/**
 * Created by AndiezSatria on 17/04/2023.
 */
interface IAppRepository {
    suspend fun insertTransaction(transaction: Transaction, userId: Int)
    suspend fun insertUser(user: User)
    fun getUser(userId: Int): Flow<User>

    fun getTransactions(): Flow<List<Transaction>>
}