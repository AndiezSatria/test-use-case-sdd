package org.andiez.testusecase.ui.navigation

import org.andiez.common.data.source.local.pref.DataConstant

/**
 * Created by AndiezSatria on 01/05/2023.
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Detail : Screen("detail/{${DataConstant.CHART_NAME_ARGS}}") {
        fun createRoute(chartName: String) = "detail/$chartName"
    }
}