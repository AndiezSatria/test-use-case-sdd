package org.andiez.testusecase.ui.navigation

/**
 * Created by AndiezSatria on 01/05/2023.
 */

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Main : Screen("main")
    object Detail : Screen("detail")
    object About : Screen("about")
    object Favorite : Screen("favorite")
}