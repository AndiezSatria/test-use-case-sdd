package org.andiez.common.data.source.remote.model

import org.andiez.common.domain.model.PieDataDomain
import org.andiez.common.domain.model.PieDataEntry
import org.andiez.common.domain.model.Transaction

/**
 * Created by AndiezSatria on 12/10/2023.
 */

data class PieDataResponse(
    val type: String,
    val data: List<PieDataEntryResponse>
)

data class PieDataEntryResponse(
    val label: String,
    val percentage: Double,
    val data: List<TransactionResponse>,
)

data class TransactionResponse(
    val transactionDate: String,
    val nominal: Long,
)

fun PieDataResponse.toDomain(): PieDataDomain = PieDataDomain(
    type = this.type,
    data = this.data.toDomains(),
)

fun entryToDomain(data: PieDataEntryResponse): PieDataEntry = PieDataEntry(
    label = data.label,
    percentage = data.percentage,
    data = transactionToDomain(data.data),
)

private fun List<PieDataEntryResponse>.toDomains(): List<PieDataEntry> = this.map {
    PieDataEntry(
        label = it.label,
        percentage = it.percentage,
        data = transactionToDomain(it.data),
    )
}

private fun transactionToDomain(datas: List<TransactionResponse>): List<Transaction> = datas.map {
    Transaction(
        transactionDate = it.transactionDate,
        nominal = it.nominal,
    )
}