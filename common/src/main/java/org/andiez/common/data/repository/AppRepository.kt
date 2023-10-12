package org.andiez.common.data.repository

import kotlinx.coroutines.flow.Flow
import org.andiez.common.data.source.local.LocalDataSource
import org.andiez.common.data.source.local.pref.BasePreference
import org.andiez.common.data.source.local.pref.DataConstant
import org.andiez.common.data.source.remote.RemoteDataSource
import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.model.User
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
) : IAppRepository {
    override suspend fun insertTransaction(transaction: Transaction, userId: Int) {
        val currentBalance = BasePreference.instance()?.getLong(DataConstant.CURRENT_BALANCE) ?: 0
        localDataSource.updateBalance(userId, currentBalance - transaction.totalTransaction)
        localDataSource.insertTransaction(transaction)
    }

    override suspend fun insertUser(user: User) {
        localDataSource.insertUser(user)
    }

    override fun getUser(userId: Int): Flow<User> {
        return localDataSource.getUser(userId)
    }

    override fun getTransactions(): Flow<List<Transaction>> {
        return localDataSource.getAllTransaction()
    }
}