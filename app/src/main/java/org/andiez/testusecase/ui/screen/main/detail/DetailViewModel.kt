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
import org.andiez.common.domain.model.PieDataEntry
import org.andiez.common.domain.usecase.AppUseCase
import org.andiez.core.BaseFlowViewModel
import org.andiez.core.common.UiState
import org.andiez.core.exception.Failure
import javax.inject.Inject

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val appUseCase: AppUseCase,
    savedStateHandle: SavedStateHandle,
) : BaseFlowViewModel<PieDataEntry>() {
    val selectedData: StateFlow<String> =
        savedStateHandle.getStateFlow(DataConstant.CHART_NAME_ARGS, "")

    init {
        val data = savedStateHandle[DataConstant.CHART_NAME_ARGS] ?: ""
        getData(data)
    }

    fun getData(chartName: String) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            appUseCase.getPieByName(chartName).catch {
                e { it.message.toString() }
                uiState.value = UiState.Error(it.message.toString())
            }.collectLatest { result ->
                result.fold(
                    { failure ->
                        when (failure) {
                            is Failure.ServerError -> {
                                e { failure.message.toString() }
                                uiState.value = UiState.Error(failure.message.toString())
                            }

                            is Failure.Nothing -> {
                                e { "Data tidak ditemukan." }
                                uiState.value = UiState.Error("Data tidak ditemukan.")
                            }

                            else -> {
                                e { "Unknown Error" }
                                uiState.value = UiState.Error("Unknown Error")
                            }
                        }
                    },
                    { data ->
                        data.let { uiState.value = UiState.Success(it) }
                    },
                )
            }
        }
    }
}