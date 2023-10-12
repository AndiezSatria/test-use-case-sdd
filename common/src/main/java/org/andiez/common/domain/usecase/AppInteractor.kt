package org.andiez.common.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.Promo
import org.andiez.common.domain.repository.IAppRepository
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either
import javax.inject.Inject

/**
 * Created by AndiezSatria on 23/04/2023.
 */
class AppInteractor @Inject constructor(private val repository: IAppRepository) : AppUseCase {
    override fun getPromos(): Flow<Either<Failure, List<Promo>>> {
        return repository.getPromos()
    }

    override fun getPromo(id: Int): Flow<Promo> {
        return repository.getPromo(id)
    }

}