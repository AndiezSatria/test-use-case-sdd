package org.andiez.testusecase.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import org.andiez.common.data.source.local.pref.DataConstant
import org.andiez.testusecase.ui.navigation.Screen
import org.andiez.testusecase.ui.screen.main.MainScreen
import org.andiez.testusecase.ui.screen.main.MainViewModel
import org.andiez.testusecase.ui.screen.main.detail.DetailScreen
import org.andiez.testusecase.ui.screen.main.detail.DetailViewModel
import org.andiez.testusecase.ui.screen.splash.SplashScreen

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Composable
fun BaseComposeApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold(
        modifier = modifier,
        bottomBar = {}
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Splash.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Splash.route) {
                SplashScreen(navigateToMainScreen = {
                    navController.navigate(Screen.Main.route)
                })
            }
            composable(Screen.Main.route) {
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    viewModel = viewModel,
                    onChartClicked = { navController.navigate(Screen.Detail.createRoute(it)) }
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(
                    navArgument(DataConstant.CHART_NAME_ARGS) {
                        type = NavType.StringType
                    }
                ),
            ) {
                val viewModel = hiltViewModel<DetailViewModel>()
                DetailScreen(viewModel = viewModel) {
                    navController.navigateUp()
                }
            }
        }
    }
}
