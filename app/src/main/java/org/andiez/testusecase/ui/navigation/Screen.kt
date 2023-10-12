package org.andiez.testusecase.ui.navigation

import org.andiez.common.data.source.local.pref.DataConstant

/**
 * Created by AndiezSatria on 01/05/2023.
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Detail : Screen("detail/{${DataConstant.PROMO_ID_ARGS}}") {
        fun createRoute(id: Int) = "detail/$id"
    }
}