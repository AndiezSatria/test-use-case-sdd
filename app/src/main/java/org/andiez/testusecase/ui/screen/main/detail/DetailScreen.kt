package org.andiez.testusecase.ui.screen.main.detail

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import org.andiez.core.common.UiState
import org.andiez.testusecase.ui.component.appbar.SecondaryAppBar
import org.andiez.testusecase.ui.component.button.PrimaryRegularButton
import org.andiez.testusecase.ui.component.item.TransactionItem
import org.andiez.testusecase.ui.theme.ActivePrimary
import org.andiez.testusecase.ui.theme.ContentPrimary

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
    Column(modifier = modifier.fillMaxSize()) {
        SecondaryAppBar(
            title = selectedData,
            actions = {},
            onBackPressed = onBackPressed,
        )
        viewModel.uiState().collectAsState().value.let { uiState ->
            when (uiState) {
                is UiState.Success -> {
                    LazyColumn(
                        state = rememberLazyListState(),
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(horizontal = 24.dp, vertical = 16.dp)
                    ) {
                        stickyHeader {
                            Text(
                                "Riwayat Transaksi",
                                style = MaterialTheme.typography.body1.copy(
                                    color = ContentPrimary,
                                    fontWeight = FontWeight.Bold,
                                ),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                        }
                        items(uiState.data.data) { data ->
                            TransactionItem(
                                amount = data.nominal,
                                date = data.transactionDate,
                                modifier = Modifier.fillMaxWidth(),
                            )
                        }
                    }
                }

                is UiState.Error -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Column(
                            modifier = modifier.padding(24.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                "Terjadi Masalah : ${uiState.errorMessage}",
                                style = MaterialTheme.typography.body2.copy(
                                    color = ContentPrimary,
                                ),
                            )
                            Spacer(modifier = Modifier.height(16.dp))
                            PrimaryRegularButton(
                                onClick = { viewModel.getData(selectedData) },
                                text = "Muat Ulang"
                            )
                        }
                    }
                }

                is UiState.Loading -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Row(
                            modifier = modifier.padding(24.dp),
                            verticalAlignment = Alignment.CenterVertically,
                        ) {
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

                else -> {}
            }
        }
    }
}