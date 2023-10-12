package org.andiez.testusecase.ui.screen.scanner

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.lifecycle.LifecycleOwner

/**
 * Created by AndiezSatria on 11/10/2023.
 */
class ScannerState(
    val lifecycleOwner: LifecycleOwner,
    val context: Context,
)

@Composable
fun rememberScannerState(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    context: Context = LocalContext.current,
): ScannerState {
    return remember {
        ScannerState(lifecycleOwner, context)
    }
}