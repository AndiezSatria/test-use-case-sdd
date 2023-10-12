package org.andiez.common.domain.model

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class PieDataDomain(
    val type: String,
    val data: List<PieDataEntry>
)

data class PieDataEntry(
    val label: String,
    val percentage: Double,
    val data: List<Transaction>,
)

data class Transaction(
    val transactionDate: String,
    val nominal: Long,
)

