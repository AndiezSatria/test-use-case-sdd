package org.andiez.testusecase.ui.component.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.andiez.common.util.CommonUtils
import org.andiez.testusecase.R
import org.andiez.testusecase.ui.theme.ContentPrimary

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BalanceCard(
    modifier: Modifier = Modifier,
    balance: Long,
    userName: String,
) {
    Card(
        modifier = modifier,
        onClick = { },
        shape = MaterialTheme.shapes.small,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                userName,
                style = MaterialTheme.typography.body1.copy(
                    color = ContentPrimary,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                stringResource(id = R.string.txt_balance),
                style = MaterialTheme.typography.body2.copy(
                    color = ContentPrimary,
                    fontWeight = FontWeight.Bold,
                )
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Rp ${CommonUtils.formatThousands(balance)}",
                style = MaterialTheme.typography.h5.copy(
                    color = ContentPrimary,
                    fontWeight = FontWeight.Normal,
                )
            )
        }
    }
}