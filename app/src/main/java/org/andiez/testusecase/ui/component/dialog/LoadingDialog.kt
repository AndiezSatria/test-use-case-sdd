package org.andiez.testusecase.ui.component.dialog

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import org.andiez.testusecase.ui.theme.ActivePrimary
import org.andiez.testusecase.ui.theme.BackgroundPrimary
import org.andiez.testusecase.ui.theme.ContentPrimary

/**
 * Created by AndiezSatria on 05/10/2023.
 */

@Composable
fun LoadingDialog(
    modifier: Modifier = Modifier,
    isLoading: Boolean,
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
                Row(
                    modifier = modifier.padding(24.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    if (isLoading) {
                        CircularProgressIndicator(
                            modifier = Modifier.width(36.dp),
                            color = ActivePrimary,
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            "Loading...",
                            style = MaterialTheme.typography.body2.copy(
                                color = ContentPrimary,
                            ),
                        )
                    }
                }
            }
        }
    }
}