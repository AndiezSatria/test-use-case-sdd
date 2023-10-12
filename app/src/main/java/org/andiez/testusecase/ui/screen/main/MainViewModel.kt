package org.andiez.testusecase.ui.screen.main

import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.andiez.common.domain.model.Promo
import org.andiez.common.domain.usecase.AppUseCase
import org.andiez.core.BaseFlowViewModel
import org.andiez.core.common.UiState
import org.andiez.core.exception.Failure
import javax.inject.Inject

/**
 * Created by AndiezSatria on 12/10/2023.
 */

@HiltViewModel
class MainViewModel @Inject constructor(private val appUseCase: AppUseCase) :
    BaseFlowViewModel<List<Promo>>() {
    init {
        getPromos()
    }

    fun getPromos() {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            appUseCase.getPromos().catch {
                uiState.value = UiState.Error(it.message.toString())
                e { it.message.toString() }
            }.collectLatest { either ->
                either.fold(
                    { failure ->
                        when (failure) {
                            is Failure.ServerError -> {
                                uiState.value = UiState.Error(failure.message.toString())
                                e { failure.message.toString() }
                            }

                            is Failure.Nothing -> {
                                uiState.value = UiState.Success(emptyList())
                                d { "Data Kosong" }
                            }

                            else -> {
                                uiState.value = UiState.Error("Unknown Error")
                                e { "Unknown Error" }
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