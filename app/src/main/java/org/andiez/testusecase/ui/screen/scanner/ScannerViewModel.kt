package org.andiez.testusecase.ui.screen.scanner

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.e
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.andiez.common.data.source.local.pref.BasePreference
import org.andiez.common.data.source.local.pref.DataConstant
import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.usecase.AppUseCase
import org.andiez.core.BaseFlowViewModel
import org.andiez.core.common.UiState
import java.util.Calendar
import javax.inject.Inject

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@HiltViewModel
class ScannerViewModel @Inject constructor(private val appUseCase: AppUseCase) :
    BaseFlowViewModel<Transaction>() {
    private val _scanned: MutableState<String> = mutableStateOf("")
    val scanned: State<String> get() = _scanned

    private val _transactionUiState: MutableStateFlow<UiState<Transaction>> =
        MutableStateFlow(UiState.None)
    val transactionUiState: StateFlow<UiState<Transaction>> get() = _transactionUiState

    fun resetQr() {
        _scanned.value = ""
        uiState.value = UiState.None
        _transactionUiState.value = UiState.None
    }

    fun createTransaction(transaction: Transaction) {
        viewModelScope.launch {
            val currentBalance = BasePreference.instance()?.getLong(DataConstant.CURRENT_BALANCE) ?: 0
            if (currentBalance < transaction.totalTransaction) {
                _transactionUiState.value = UiState.Error("Saldo tidak mencukupi.")
            }
            transaction.transactionDate = Calendar.getInstance().timeInMillis
            val userId = BasePreference.instance()?.getInt(DataConstant.USER_ID) ?: 1
            appUseCase.insertTransaction(transaction, userId).also {
                _transactionUiState.value = UiState.Success(transaction)
            }
        }
    }

    fun onQrScanned(scanned: String) {
        viewModelScope.launch {
            uiState.value = UiState.Loading
            _scanned.value = scanned
            delay(2000L)
            try {
                val data = scanned.split(".")
                if (data.size != 4 || !data[1].contains("id", ignoreCase = true)) {
                    uiState.value = UiState.Error("Kode QR tidak dikenali.")
                } else {
                    val transaction = Transaction(
                        id = 0,
                        idTransaction = data[1],
                        paymentSource = data[0],
                        merchantName = data[2],
                        totalTransaction = data[3].toLong(),
                        0
                    )
                    uiState.value = UiState.Success(transaction)
                }
            } catch (e: Exception) {
                e { e.message.toString() }
                uiState.value = UiState.Error("Kode QR tidak dikenali.")
            }
        }
    }
}