package org.andiez.testusecase.ui.component.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.andiez.testusecase.ui.component.button.TertiaryNeutralButton
import org.andiez.testusecase.ui.theme.BackgroundPrimary
import org.andiez.testusecase.ui.theme.ContentPrimary
import org.andiez.testusecase.ui.theme.ErrorPrimary
import org.andiez.testusecase.ui.theme.GreenHealth

/**
 * Created by AndiezSatria on 05/10/2023.
 */

@Composable
fun InformationDialog(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            color = BackgroundPrimary,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(BackgroundPrimary)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.copy(
                            color = ContentPrimary,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    TertiaryNeutralButton(onClick = onClick, text = "OK")
                }
            }
        }
    }
}

@Composable
fun ConfirmationDialog(
    modifier: Modifier = Modifier,
    text: String,
    positiveText: String = "OK",
    negativeText: String = "Cancel",
    onPositiveClick: () -> Unit,
    onNegativeClick: () -> Unit,
    onDismiss: () -> Unit,
) {
    Dialog(
        onDismissRequest = { onDismiss() },
        properties = DialogProperties(
            dismissOnBackPress = false,
            dismissOnClickOutside = false,
        )
    ) {
        Surface(
            shape = MaterialTheme.shapes.small,
            color = BackgroundPrimary,
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .background(BackgroundPrimary)
                        .padding(24.dp),
                    horizontalAlignment = Alignment.End
                ) {
                    Text(
                        text = text,
                        style = MaterialTheme.typography.body1.copy(
                            color = ContentPrimary,
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        TertiaryNeutralButton(
                            onClick = onNegativeClick,
                            text = negativeText,
                            textColor = ErrorPrimary,
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        TertiaryNeutralButton(
                            onClick = onPositiveClick,
                            text = positiveText,
                            textColor = GreenHealth,
                        )
                    }
                }
            }
        }
    }
}