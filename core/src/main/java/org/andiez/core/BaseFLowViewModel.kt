package org.andiez.core

import android.util.Log
import androidx.lifecycle.ViewModel
import org.andiez.core.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withTimeout

/**
 * Created by AndiezSatria on 10/04/2023.
 */

abstract class BaseFlowViewModel<T> : ViewModel() {

    private val defaultNumberOfRetries = 3
    private val defaultTimeout = 10000L // 10sec

    fun uiState(): StateFlow<UiState<T>> = uiState
    protected val uiState: MutableStateFlow<UiState<T>> = MutableStateFlow(UiState.None)

    protected suspend fun <T> retryWithTimeout(
        numberOfRetries: Int = defaultNumberOfRetries,
        timeout: Long = defaultTimeout,
        block: suspend () -> T
    ) = retry(numberOfRetries) {
        withTimeout(timeout) {
            block()
        }
    }

    protected suspend fun <T> retry(
        numberOfRetries: Int,
        delayBetweenRetries: Long = 100,
        block: suspend () -> T
    ): T {
        repeat(numberOfRetries) {
            try {
                return block()
            } catch (exception: Exception) {
                Log.e("error", exception.localizedMessage)
            }
            delay(delayBetweenRetries)
        }
        return block() // last attempt
    }
}
