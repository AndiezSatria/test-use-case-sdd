package org.andiez.common.domain.model

import org.andiez.common.data.source.local.entity.TransactionEntity

/**
 * Created by AndiezSatria on 11/10/2023.
 */
data class Transaction(
    val id: Int,
    val idTransaction: String,
    val paymentSource: String,
    val merchantName: String,
    val totalTransaction: Long,
    var transactionDate: Long,
)

fun List<Transaction>.toEntities(): List<TransactionEntity> = this.map { domain ->
    TransactionEntity(
        id = domain.id,
        idTransaction = domain.idTransaction,
        paymentSource = domain.paymentSource,
        merchantName = domain.merchantName,
        totalTransaction = domain.totalTransaction,
        transactionDate = domain.transactionDate,
    )
}

fun Transaction.toEntity(): TransactionEntity = TransactionEntity(
    id = this.id,
    idTransaction = this.idTransaction,
    paymentSource = this.paymentSource,
    merchantName = this.merchantName,
    totalTransaction = this.totalTransaction,
    transactionDate = this.transactionDate,
)