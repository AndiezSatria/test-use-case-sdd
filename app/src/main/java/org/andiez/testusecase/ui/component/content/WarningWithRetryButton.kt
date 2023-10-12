package org.andiez.testusecase.ui.component.content

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.andiez.testusecase.ui.component.button.PrimaryRegularButton
import org.andiez.testusecase.ui.theme.ContentPrimary

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@Composable
fun WarningWithRetryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    message: String = "",
    isEmpty: Boolean,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                if (isEmpty) "Data yang dicari masih kosong." else "Terjadi Masalah : $message",
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.body2.copy(
                    color = ContentPrimary,
                ),
            )
            Spacer(modifier = Modifier.height(16.dp))
            PrimaryRegularButton(
                onClick = { onClick() },
                text = "Muat Ulang"
            )
        }
    }
}