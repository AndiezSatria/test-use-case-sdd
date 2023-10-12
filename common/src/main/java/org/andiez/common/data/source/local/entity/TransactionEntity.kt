package org.andiez.common.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.andiez.common.domain.model.Transaction

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Entity(tableName = "TransactionTable")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val idTransaction: String,
    val paymentSource: String,
    val merchantName: String,
    val totalTransaction: Long,
    val transactionDate: Long,
)

fun List<TransactionEntity>.toDomain() : List<Transaction> = this.map { entity ->
    Transaction(
        id = entity.id,
        idTransaction = entity.idTransaction,
        paymentSource = entity.paymentSource,
        merchantName = entity.merchantName,
        totalTransaction = entity.totalTransaction,
        transactionDate = entity.transactionDate,
    )
}
