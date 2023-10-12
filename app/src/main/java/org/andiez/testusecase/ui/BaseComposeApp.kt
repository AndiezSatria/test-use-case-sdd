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
import org.andiez.testusecase.ui.screen.main.SplashScreen
import org.andiez.testusecase.ui.screen.scanner.ScannerScreen
import org.andiez.testusecase.ui.screen.scanner.ScannerViewModel
import org.andiez.testusecase.ui.screen.splash.MainScreen
import org.andiez.testusecase.ui.screen.splash.MainViewModel
import org.andiez.testusecase.ui.screen.success.SuccessScanner
import org.andiez.testusecase.ui.screen.success.SuccessViewModel

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
                SplashScreen(
                    navigateToMainScreen = {
                        navController.popBackStack()
                        navController.navigate(Screen.Main.route)
                    },
                )
            }
            composable(Screen.Main.route) {
                val viewModel = hiltViewModel<MainViewModel>()
                MainScreen(
                    viewModel = viewModel,
                    navigateToScannerScreen = {
                        navController.navigate(Screen.QrCode.route)
                    },
                )
            }
            composable(Screen.QrCode.route) {
                val viewModel = hiltViewModel<ScannerViewModel>()
                ScannerScreen(
                    viewModel = viewModel,
                    navigateToSuccessScreen = { merchant, amount ->
                        navController.navigate(Screen.Success.createRoute(merchant, amount))
                    },
                )
            }
            composable(
                route = Screen.Success.route,
                arguments = listOf(
                    navArgument(DataConstant.MERCHANT_ARGS) {
                        type = NavType.StringType
                    },
                    navArgument(DataConstant.AMOUNT_ARGS) {
                        type = NavType.LongType
                    },
                ),
            ) {
                val viewModel = hiltViewModel<SuccessViewModel>()
                SuccessScanner(
                    viewModel = viewModel,
                    goToMainScreen = {
                        navController.popBackStack(Screen.Main.route, inclusive = false)
                    },
                )
            }
        }
    }
}
