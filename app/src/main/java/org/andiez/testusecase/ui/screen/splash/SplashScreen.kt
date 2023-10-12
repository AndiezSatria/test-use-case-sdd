package org.andiez.testusecase.ui.screen.splash

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import org.andiez.testusecase.ui.theme.ActivePrimary
import org.andiez.testusecase.ui.theme.BackgroundPrimary

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Composable
fun SplashScreen(
    modifier: Modifier = Modifier,
    navigateToMainScreen: () -> Unit,
) {
    LaunchedEffect(key1 = true) {
        delay(3000)
        navigateToMainScreen()
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(ActivePrimary)
    ) {
        Text(
            text = "Chart Portofolio",
            style = MaterialTheme.typography.h6.copy(
                color = BackgroundPrimary
            ),
            modifier = Modifier.align(Alignment.Center),
        )
        CircularProgressIndicator(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .width(36.dp)
                .padding(bottom = 24.dp),
            color = BackgroundPrimary,
        )
    }
}