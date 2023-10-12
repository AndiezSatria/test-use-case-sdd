package org.andiez.testusecase.ui.screen.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.ajalt.timberkt.d
import com.github.ajalt.timberkt.e
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.andiez.common.data.source.local.pref.BasePreference
import org.andiez.common.data.source.local.pref.DataConstant
import org.andiez.common.domain.model.Transaction
import org.andiez.common.domain.model.User
import org.andiez.common.domain.usecase.AppUseCase
import org.andiez.core.common.UiState
import org.andiez.core.common.WhileUiSubscribed
import javax.inject.Inject

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@HiltViewModel
class MainViewModel @Inject constructor(private val appUseCase: AppUseCase) : ViewModel() {
    private var user: MutableStateFlow<UiState<User>> = MutableStateFlow(UiState.Loading)
    private var transactions: MutableStateFlow<UiState<List<Transaction>>> =
        MutableStateFlow(UiState.Loading)
    private val error = MutableStateFlow(Pair(false, ""))
    fun onDismissDialog() {
        error.value = Pair(false, "")
    }

    init {
        val userId = BasePreference.instance()?.getInt(DataConstant.USER_ID)
        d { "User ID : $userId" }
        if (userId == null || userId == 0) {
            val newUser = User(1, "Mohammad Andiez Satria Permana", 1000000)
            viewModelScope.launch {
                appUseCase.insertUser(newUser)
            }
            BasePreference.instance()?.saveInt(DataConstant.USER_ID, newUser.id)
            getUser(newUser.id)
            getTransactions()
        } else {
            getUser(userId)
            getTransactions()
        }
    }

    private fun getUser(userId: Int) {
        viewModelScope.launch {
            appUseCase.getUser(userId)
                .catch { failure ->
                    user.value = UiState.Error(failure.message.toString())
                    error.value = Pair(true, failure.message.toString())
                    e { failure.message.toString() }
                }.collectLatest { data ->
                    d { "Data USer : ${data.userName}, ${data.balance}" }
                    BasePreference.instance()
                        ?.saveLong(DataConstant.CURRENT_BALANCE, data.balance)
                    user.value = UiState.Success(data)
                }
        }
    }

    private fun getTransactions() {
        viewModelScope.launch {
            appUseCase.getTransactions()
                .catch { failure ->
                    transactions.value = UiState.Error(failure.message.toString())
                    error.value = Pair(true, failure.message.toString())
                    e { failure.message.toString() }
                }.collectLatest { data ->
                    d { "Data Transaction : ${data.size}" }
                    transactions.value = UiState.Success(data)
                }
        }
    }

    val uiState: StateFlow<MainUiState>
        get() = combine(
            user,
            transactions,
            error
        ) { userUiState, transactionUiState, errorOccurred ->
            MainUiState(
                userUiState,
                transactionUiState,
                errorOccurred,
            )
        }.stateIn(
            scope = viewModelScope,
            started = WhileUiSubscribed,
            initialValue = MainUiState(
                UiState.Loading,
                UiState.Loading,
                Pair(false, ""),
            )
        )
}