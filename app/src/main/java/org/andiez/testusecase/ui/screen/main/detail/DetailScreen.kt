package org.andiez.testusecase.ui.screen.main.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import org.andiez.common.domain.model.Promo
import org.andiez.core.common.UiState
import org.andiez.testusecase.ui.component.appbar.SecondaryAppBar
import org.andiez.testusecase.ui.component.content.LoadingContent
import org.andiez.testusecase.ui.component.content.WarningWithRetryButton
import org.andiez.testusecase.ui.theme.ContentPrimary
import org.andiez.testusecase.ui.theme.MuteSecondary

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DetailScreen(
    modifier: Modifier = Modifier,
    viewModel: DetailViewModel,
    onBackPressed: () -> Unit,
) {
    val selectedData = viewModel.selectedData.collectAsState().value
    val selectedPromoUiState = viewModel.uiState().collectAsState().value
    Column(modifier = modifier.fillMaxSize()) {
        SecondaryAppBar(
            title = if (selectedPromoUiState is UiState.Success) selectedPromoUiState.data.name else "Promo",
            actions = {},
            onBackPressed = onBackPressed,
        )
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            when (selectedPromoUiState) {
                is UiState.Success -> {
                    DetailContent(
                        data = selectedPromoUiState.data,
                        modifier = Modifier.fillMaxSize()
                    )
                }

                is UiState.Loading -> {
                    LoadingContent()
                }

                is UiState.Error -> {
                    WarningWithRetryButton(
                        onClick = { viewModel.getPromoById(selectedData) },
                        isEmpty = false,
                        message = selectedPromoUiState.errorMessage,
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
fun DetailContent(
    modifier: Modifier = Modifier,
    data: Promo,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MuteSecondary),
                model = data.image.formats.mediumUrl,
                contentDescription = data.image.caption,
                contentScale = ContentScale.Crop,
            )
        }
        Text(
            data.name,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            style = MaterialTheme.typography.h5.copy(
                color = ContentPrimary,
                fontWeight = FontWeight.SemiBold,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            data.desc,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 24.dp),
            style = MaterialTheme.typography.body2.copy(
                color = ContentPrimary,
            )
        )
    }
}