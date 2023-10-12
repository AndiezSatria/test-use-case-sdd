package org.andiez.common.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.model.User
import org.andiez.common.domain.repository.IAppRepository
import javax.inject.Inject

/**
 * Created by AndiezSatria on 23/04/2023.
 */
class AppInteractor @Inject constructor(private val repository: IAppRepository) : AppUseCase {
    override suspend fun insertTransaction(transaction: Transaction, userId: Int) {
        repository.insertTransaction(transaction, userId)
    }

    override suspend fun insertUser(user: User) {
        repository.insertUser(user)
    }

    override fun getUser(userId: Int): Flow<User> = repository.getUser(userId)

    override fun getTransactions(): Flow<List<Transaction>> = repository.getTransactions()

}