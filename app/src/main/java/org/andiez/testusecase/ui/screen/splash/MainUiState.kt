package org.andiez.testusecase.ui.screen.splash

import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.model.User
import org.andiez.core.common.UiState

/**
 * Created by AndiezSatria on 11/10/2023.
 */
data class MainUiState(
    val userUiState: UiState<User>,
    val transactionsUiState: UiState<List<Transaction>>,
    val error: Pair<Boolean, String>
)