package org.andiez.testusecase.ui.screen.success

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.andiez.common.data.source.local.pref.DataConstant
import javax.inject.Inject

/**
 * Created by AndiezSatria on 11/10/2023.
 */
@HiltViewModel
class SuccessViewModel @Inject constructor(savedStateHandle: SavedStateHandle) : ViewModel() {
    val merchantName: String = savedStateHandle[DataConstant.MERCHANT_ARGS] ?: ""
    val amount: Long = savedStateHandle[DataConstant.AMOUNT_ARGS] ?: 0
}