package org.andiez.testusecase.ui.component.item

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.andiez.common.util.CommonUtils
import org.andiez.testusecase.ui.theme.ContentPrimary
import org.andiez.testusecase.ui.theme.ContentTertiary

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    amount: Long,
    date: String,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            "Rp ${CommonUtils.formatThousands(amount)}",
            style = MaterialTheme.typography.body2.copy(
                color = ContentPrimary,
            ),
        )
        Text(
            date,
            style = MaterialTheme.typography.body2.copy(
                color = ContentTertiary,
            ),
        )
    }
}