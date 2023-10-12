package org.andiez.testusecase.ui.navigation

import org.andiez.common.data.source.local.pref.DataConstant

/**
 * Created by AndiezSatria on 01/05/2023.
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object QrCode : Screen("qr_code")
    object Success :
        Screen("success/{${DataConstant.MERCHANT_ARGS}}/{${DataConstant.AMOUNT_ARGS}}") {
        fun createRoute(merchantName: String, transactionAmount: Long) =
            "success/$merchantName/$transactionAmount"
    }
}