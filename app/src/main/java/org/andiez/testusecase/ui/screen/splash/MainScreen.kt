package org.andiez.testusecase.ui.screen.splash

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import org.andiez.common.domain.model.User
import org.andiez.core.common.UiState
import org.andiez.testusecase.R
import org.andiez.testusecase.ui.component.appbar.SecondaryAppBar
import org.andiez.testusecase.ui.component.button.PrimaryRegularButton
import org.andiez.testusecase.ui.component.card.BalanceCard
import org.andiez.testusecase.ui.component.dialog.InformationDialog
import org.andiez.testusecase.ui.component.dialog.LoadingDialog
import org.andiez.testusecase.ui.component.item.TransactionItem
import org.andiez.testusecase.ui.theme.BackgroundTertiary
import org.andiez.testusecase.ui.theme.ContentPrimary
import org.andiez.testusecase.ui.theme.MutePrimary

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    viewModel: MainViewModel,
    navigateToScannerScreen: () -> Unit,
) {
    val mainUiState: MainUiState by viewModel.uiState.collectAsState()
    val mainState = rememberMainState()

    if (mainUiState.transactionsUiState is UiState.Loading || mainUiState.userUiState is UiState.Loading) {
        LoadingDialog(
            isLoading = mainUiState.transactionsUiState is UiState.Loading || mainUiState.userUiState is UiState.Loading,
            onDismiss = {},
        )
    }

    if (mainUiState.error.first) {
        InformationDialog(
            text = mainUiState.error.second,
            onClick = { viewModel.onDismissDialog() },
            onDismiss = {},
        )
    }

    Column(modifier = modifier.fillMaxSize()) {
        SecondaryAppBar(
            title = stringResource(id = R.string.txt_main_screen_title),
            actions = {},
        )
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            state = mainState.lazyListState,
            contentPadding = PaddingValues(horizontal = 24.dp),
        ) {
            item {
                when (val userUiState = mainUiState.userUiState) {
                    is UiState.Success -> {
                        MainBalanceContent(
                            user = userUiState.data,
                            onClick = { navigateToScannerScreen() },
                        )
                    }

                    else -> {}
                }
            }
            stickyHeader {
                Text(
                    stringResource(id = R.string.txt_transaction_history),
                    style = MaterialTheme.typography.h6.copy(
                        color = ContentPrimary,
                    ),
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
            when (val transactionUiState = mainUiState.transactionsUiState) {
                is UiState.Success -> {
                    if (transactionUiState.data.isEmpty()) {
                        item {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Spacer(modifier = Modifier.height(24.dp))
                                Box(
                                    modifier = Modifier
                                        .width(150.dp)
                                        .height(75.dp)
                                        .background(MutePrimary),
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                Text(
                                    text = stringResource(id = R.string.txt_transaction_empty),
                                    style = MaterialTheme.typography.body1.copy(
                                        color = BackgroundTertiary,
                                    ),
                                    textAlign = TextAlign.Center,
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 18.dp)
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                            }
                        }
                    } else {
                        itemsIndexed(
                            transactionUiState.data,
                            key = { _, item -> item.id }) { index, data ->
                            TransactionItem(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                transaction = data,
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            if (index != transactionUiState.data.lastIndex) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(1.dp)
                                        .background(MutePrimary),
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                            }
                        }
                    }
                }

                else -> {}
            }
        }
    }
}

@Composable
fun MainBalanceContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    user: User,
) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(24.dp))
        BalanceCard(
            balance = user.balance,
            userName = user.userName,
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))
        PrimaryRegularButton(
            onClick = { onClick() },
            text = stringResource(id = R.string.txt_scan_qr),
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(24.dp))
    }

}