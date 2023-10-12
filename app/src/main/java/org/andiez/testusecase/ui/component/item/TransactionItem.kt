package org.andiez.testusecase.ui.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.andiez.common.domain.model.Transaction
import org.andiez.common.util.CommonUtils
import org.andiez.testusecase.ui.theme.ContentPrimary
import org.andiez.testusecase.ui.theme.ContentTertiary

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: Transaction,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.Start,
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top,
            modifier = Modifier.fillMaxWidth(),
        ) {
            Column(horizontalAlignment = Alignment.Start) {
                Text(
                    transaction.merchantName,
                    style = MaterialTheme.typography.body2.copy(
                        color = ContentPrimary,
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    CommonUtils.getCalendarString(
                        "dd MMM yyyy, HH:mm",
                        transaction.transactionDate
                    ),
                    style = MaterialTheme.typography.caption.copy(
                        color = ContentTertiary,
                    ),
                )
            }
            Text(
                "Rp ${CommonUtils.formatThousands(transaction.totalTransaction)}",
                style = MaterialTheme.typography.body2.copy(
                    color = ContentPrimary,
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}