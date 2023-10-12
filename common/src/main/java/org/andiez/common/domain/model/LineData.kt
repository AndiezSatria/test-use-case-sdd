package org.andiez.common.domain.model

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class LineData(
    val type: String,
    val data: LineEntry
)

data class LineEntry(
    val monthly: List<Int>,
)
