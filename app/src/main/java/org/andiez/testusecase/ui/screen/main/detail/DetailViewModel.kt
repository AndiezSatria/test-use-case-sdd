package org.andiez.testusecase.ui.screen.main.detail

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.e
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.andiez.common.data.source.local.pref.DataConstant
import org.andiez.common.domain.model.Promo
import org.andiez.common.domain.usecase.AppUseCase
import org.andiez.core.BaseFlowViewModel
import org.andiez.core.common.UiState
import javax.inject.Inject

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appUseCase: AppUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseFlowViewModel<Promo>() {
    val selectedData: StateFlow<Int> =
        savedStateHandle.getStateFlow(DataConstant.PROMO_ID_ARGS, 0)

    init {
        val data = savedStateHandle[DataConstant.PROMO_ID_ARGS] ?: 0

        getPromoById(data)
    }

    fun getPromoById(id: Int) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            appUseCase.getPromo(id).catch {
                uiState.value = UiState.Error(it.message.toString())
                e { it.message.toString() }
            }.collectLatest { data ->
                uiState.value = UiState.Success(data)
            }
        }
    }


}