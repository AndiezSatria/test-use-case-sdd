package org.andiez.common.domain.usecase

import kotlinx.coroutines.flow.Flow
import org.andiez.common.domain.model.LineData
import org.andiez.common.domain.model.PieDataDomain
import org.andiez.common.domain.model.PieDataEntry
import org.andiez.core.exception.Failure
import org.andiez.core.functional.Either

/**
 * Created by AndiezSatria on 23/04/2023.
 */
interface AppUseCase {
    fun getPieData(): Flow<Either<Failure, PieDataDomain>>
    fun getLineData(): Flow<Either<Failure, LineData>>

    fun getPieByName(chartName: String): Flow<Either<Failure, PieDataEntry>>
}