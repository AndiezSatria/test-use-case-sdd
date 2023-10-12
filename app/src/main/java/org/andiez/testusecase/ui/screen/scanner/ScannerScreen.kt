package org.andiez.testusecase.ui.screen.scanner

import android.util.Size
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageAnalysis
import androidx.camera.core.ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.MultiplePermissionsState
import org.andiez.common.util.CommonUtils
import org.andiez.common.util.QrCodeAnalyzer
import org.andiez.core.common.UiState
import org.andiez.testusecase.R
import org.andiez.testusecase.state.rememberAppPermissionState
import org.andiez.testusecase.ui.component.appbar.SecondaryBackgroundAppBar
import org.andiez.testusecase.ui.component.dialog.ConfirmationDialog
import org.andiez.testusecase.ui.component.dialog.InformationDialog
import org.andiez.testusecase.ui.component.dialog.LoadingDialog

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerScreen(
    modifier: Modifier = Modifier,
    viewModel: ScannerViewModel,
    navigateToSuccessScreen: (String, Long) -> Unit,
) {
    val permissionState = rememberAppPermissionState()
    val screenState = rememberScannerState()
    val cameraProvideFuture = remember {
        ProcessCameraProvider.getInstance(screenState.context)
    }

    if (!permissionState.permissionState.allPermissionsGranted) {
        LaunchedEffect(key1 = true) {
            permissionState.permissionState.launchMultiplePermissionRequest()
        }
    }
    viewModel.transactionUiState.collectAsState().value.let { uiState ->
        when (uiState) {
            is UiState.Success -> {
                viewModel.resetQr()
                navigateToSuccessScreen(uiState.data.merchantName, uiState.data.totalTransaction)
            }

            is UiState.Error -> {
                InformationDialog(
                    text = uiState.errorMessage,
                    onClick = { viewModel.resetQr() },
                    onDismiss = {},
                )
            }

            is UiState.Loading -> {
                LoadingDialog(isLoading = true, onDismiss = {})
            }

            else -> {}
        }
    }
    viewModel.uiState().collectAsState().value.let { uiState ->
        when (uiState) {
            is UiState.Success -> {
                ConfirmationDialog(
                    text = "Apakah Anda ingin melanjutkan pembayaran di ${uiState.data.merchantName} dengan nominal Rp ${
                        CommonUtils.formatThousands(
                            uiState.data.totalTransaction
                        )
                    }?",
                    positiveText = stringResource(id = R.string.txt_confirm),
                    negativeText = stringResource(id = R.string.txt_cancel),
                    onPositiveClick = { viewModel.createTransaction(uiState.data) },
                    onNegativeClick = { viewModel.resetQr() },
                    onDismiss = { viewModel.resetQr() },
                )
            }

            is UiState.Error -> {
                InformationDialog(
                    text = uiState.errorMessage,
                    onClick = { viewModel.resetQr() },
                    onDismiss = {},
                )
            }

            is UiState.Loading -> {
                LoadingDialog(isLoading = true, onDismiss = {})
            }

            else -> {}
        }
    }

    Column(
        modifier = modifier.fillMaxSize(),
    ) {
        SecondaryBackgroundAppBar(
            title = stringResource(id = R.string.txt_scanner_screen_title),
            actions = {},
        )
        viewModel.uiState().collectAsState().value.let { uiState ->
            ScannerContent(
                permissionState = permissionState.permissionState,
                cameraProviderFuture = cameraProvideFuture.get(),
                onQrScanned = {
                    if (uiState is UiState.None)
                        viewModel.onQrScanned(it)
                },
                lifecycleOwner = screenState.lifecycleOwner,
            )
        }
    }
}

@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun ScannerContent(
    modifier: Modifier = Modifier,
    permissionState: MultiplePermissionsState,
    cameraProviderFuture: ProcessCameraProvider,
    onQrScanned: (String) -> Unit,
    lifecycleOwner: LifecycleOwner,
) {
    if (permissionState.allPermissionsGranted) {
        AndroidView(
            factory = { context ->
                val previewView = PreviewView(context)
                val preview = Preview.Builder().build()
                val selector = CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build()
                preview.setSurfaceProvider(previewView.surfaceProvider)

                val imageAnalysis = ImageAnalysis.Builder()
                    .setTargetResolution(
                        Size(
                            previewView.width,
                            previewView.height
                        )
                    )
                    .setBackpressureStrategy(STRATEGY_KEEP_ONLY_LATEST)
                    .build()

                imageAnalysis.setAnalyzer(
                    ContextCompat.getMainExecutor(context),
                    QrCodeAnalyzer { result ->
                        onQrScanned(result)
                    }
                )
                try {
                    cameraProviderFuture.bindToLifecycle(
                        lifecycleOwner,
                        selector,
                        preview,
                        imageAnalysis
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                previewView
            },
            modifier = modifier.fillMaxSize(),
        )
    }
}