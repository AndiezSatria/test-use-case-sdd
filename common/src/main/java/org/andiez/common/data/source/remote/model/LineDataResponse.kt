package org.andiez.common.data.source.remote.model

import org.andiez.common.domain.model.LineData
import org.andiez.common.domain.model.LineEntry

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class LineDataResponse(
    val type: String,
    val data: LineEntryResponse
)

data class LineEntryResponse(
    val monthly: List<Int>,
)

fun LineDataResponse.toDomain(): LineData = LineData(
    type = this.type,
    data = this.data.toDomain(),
)

fun LineEntryResponse.toDomain(): LineEntry = LineEntry(
    monthly = this.monthly,
)

