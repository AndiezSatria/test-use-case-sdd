package org.andiez.testusecase.ui.screen.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.e
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.andiez.common.domain.model.LineData
import org.andiez.common.domain.model.PieDataDomain
import org.andiez.common.domain.usecase.AppUseCase
import org.andiez.core.common.UiState
import org.andiez.core.common.WhileUiSubscribed
import org.andiez.core.exception.Failure
import javax.inject.Inject

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@HiltViewModel
class MainViewModel @Inject constructor(private val appUseCase: AppUseCase) : ViewModel() {
    private val pieData: MutableStateFlow<UiState<PieDataDomain>> =
        MutableStateFlow(UiState.Loading)
    private val lineData: MutableStateFlow<UiState<LineData>> = MutableStateFlow(UiState.Loading)

    init {
        getPieData()
        getLineData()
    }

    fun getLineData() {
        viewModelScope.launch {
            lineData.value = UiState.Loading
            appUseCase.getLineData().catch {
                e { it.message.toString() }
                lineData.value = UiState.Error(it.message.toString())
            }.collectLatest { result ->
                result.fold(
                    { failure ->
                        when (failure) {
                            is Failure.ServerError -> {
                                e { failure.message.toString() }
                                lineData.value = UiState.Error(failure.message.toString())
                            }

                            else -> {
                                e { "Unknown Error" }
                                lineData.value = UiState.Error("Unknown Error")
                            }
                        }
                    },
                    { data ->
                        data.let { lineData.value = UiState.Success(it) }
                    },
                )
            }
        }
    }

    fun getPieData() {
        viewModelScope.launch {
            pieData.value = UiState.Loading
            appUseCase.getPieData().catch {
                e { it.message.toString() }
                pieData.value = UiState.Error(it.message.toString())
            }.collectLatest { result ->
                result.fold(
                    { failure ->
                        when (failure) {
                            is Failure.ServerError -> {
                                e { failure.message.toString() }
                                pieData.value = UiState.Error(failure.message.toString())
                            }

                            else -> {
                                e { "Unknown Error" }
                                pieData.value = UiState.Error("Unknown Error")
                            }
                        }
                    },
                    { data ->
                        data.let { pieData.value = UiState.Success(it) }
                    },
                )
            }
        }
    }

    val uiState: StateFlow<MainUiState>
        get() = combine(
            pieData,
            lineData
        ) { pieDataUiState, lineDataUiState ->
            MainUiState(pieDataUiState, lineDataUiState)
        }.stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = MainUiState(
                UiState.Loading,
                UiState.Loading,
            )
        )
}