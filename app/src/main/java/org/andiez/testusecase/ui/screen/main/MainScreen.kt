package org.andiez.testusecase.ui.screen.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.github.ajalt.timberkt.d
import org.andiez.common.domain.model.Promo
import org.andiez.core.common.UiState
import org.andiez.testusecase.R
import org.andiez.testusecase.ui.component.appbar.SecondaryAppBar
import org.andiez.testusecase.ui.component.content.LoadingContent
import org.andiez.testusecase.ui.component.content.WarningWithRetryButton
import org.andiez.testusecase.ui.component.item.PromoItem

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    onPromoClicked: (Int) -> Unit,
) {

    Column(modifier = modifier.fillMaxSize()) {
        SecondaryAppBar(
            title = stringResource(id = R.string.txt_app_title),
            actions = {},
        )
        viewModel.uiState().collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    MainContent(data = uiState.data, onPromoClicked = { onPromoClicked(it) })
                }

                is UiState.Loading -> {
                    LoadingContent()
                }

                is UiState.Error -> {
                    WarningWithRetryButton(
                        onClick = { viewModel.getPromos() },
                        isEmpty = false,
                        message = uiState.errorMessage,
                    )
                }

                else -> {}
            }
        }
    }
}

@Composable
fun MainContent(
    modifier: Modifier = Modifier,
    data: List<Promo>,
    onPromoClicked: (Int) -> Unit,
) {
    LazyColumn(
        state = rememberLazyListState(),
        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp),
        modifier = modifier.fillMaxSize(),
    ) {
        items(data, key = { it.id }) { item ->
            d { "Promo : ${item.name}" }
            PromoItem(
                onClick = { onPromoClicked(item.id) },
                item = item,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
            )
        }
    }
}