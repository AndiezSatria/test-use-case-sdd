package org.andiez.common.domain.usecase

import org.andiez.common.domain.repository.IAppRepository
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either
import org.andiez.core.usecase.UseCase
import javax.inject.Inject

/**
 * Created by AndiezSatria on 23/04/2023.
 */
class AppInteractor @Inject constructor(repository: IAppRepository) : AppUseCase {

}