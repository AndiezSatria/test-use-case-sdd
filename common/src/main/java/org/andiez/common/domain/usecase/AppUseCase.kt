package org.andiez.common.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.Promo
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either

/**
 * Created by AndiezSatria on 23/04/2023.
 */
interface AppUseCase {
    fun getPromos(): Flow<Either<Failure, List<Promo>>>

    fun getPromo(id: Int): Flow<Promo>
}
