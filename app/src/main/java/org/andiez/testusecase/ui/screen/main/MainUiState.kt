package org.andiez.testusecase.ui.screen.main

import org.andiez.common.domain.model.LineData
import org.andiez.common.domain.model.PieDataDomain
import org.andiez.core.common.UiState

/**
 * Created by AndiezSatria on 12/10/2023.
 */
data class MainUiState(
    val pieUiState: UiState<PieDataDomain>,
    val lineUiState: UiState<LineData>,
)