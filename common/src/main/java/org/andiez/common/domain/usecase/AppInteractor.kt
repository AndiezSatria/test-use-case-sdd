package org.andiez.common.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.LineData
import org.andiez.common.domain.model.PieDataDomain
import org.andiez.common.domain.model.PieDataEntry
import org.andiez.common.domain.repository.IAppRepository
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either
import javax.inject.Inject

/**
 * Created by AndiezSatria on 23/04/2023.
 */
class AppInteractor @Inject constructor(private val repository: IAppRepository) : AppUseCase {
    override fun getPieData(): Flow<Either<Failure, PieDataDomain>> {
        return repository.getPieData()
    }

    override fun getLineData(): Flow<Either<Failure, LineData>> {
        return repository.getLineData()
    }

    override fun getPieByName(chartName: String): Flow<Either<Failure, PieDataEntry>> {
        return repository.getPieByName(chartName)
    }

}