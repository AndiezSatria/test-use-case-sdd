package org.andiez.common.data.source.local

import com.github.ajalt.timberkt.d
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import org.andiez.common.data.source.local.dao.AppDao
import org.andiez.common.data.source.local.entity.toDomain
import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.model.User
import org.andiez.common.domain.model.toEntity
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Singleton
class LocalDataSourceImpl @Inject constructor(private val appDao: AppDao) : LocalDataSource {
    override suspend fun insertTransaction(transaction: Transaction) {
        appDao.insertTransaction(transaction.toEntity())
    }

    override fun getAllTransaction(): Flow<List<Transaction>> {
        return appDao.getAllTransaction().map { it.toDomain() }
    }

    override suspend fun insertUser(user: User) {
        d { "Insert user : ${user.userName}" }
        appDao.insertUser(user.toEntity())
    }

    override fun getUser(userId: Int): Flow<User> {
        val data = appDao.getUser(userId)
        d { "Get user : $userId" }
        return data.map { it.toDomain() }
    }

    override suspend fun updateBalance(id: Int, transactionAmount: Long) {
        d { "Update Balance : $id, $transactionAmount" }
        appDao.updateUserBalance(id, transactionAmount)
    }
}