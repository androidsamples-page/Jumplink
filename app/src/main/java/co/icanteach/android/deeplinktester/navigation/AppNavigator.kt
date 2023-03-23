package co.icanteach.android.deeplinktester.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import co.icanteach.android.deeplinktester.deeplinkhistory.presentation.DeepLinkHistoryScreen
import co.icanteach.android.deeplinktester.home.HomeScreen
import co.icanteach.android.deeplinktester.settings.SettingsScreen

@Composable
fun AppNavigator() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route
    ) {
        composable(Screens.HomeScreen.route) {
            HomeScreen {
                navController.navigate(Screens.SettingsScreen.route)
            }
        }

        composable(
            route = Screens.SettingsScreen.route
        ) {
            SettingsScreen(
                onNavigateHistoryScreenClicked = {
                    navController.navigate(Screens.HistoryScreen.route)
                }
            )
        }

        composable(
            route = Screens.HistoryScreen.route
        ) {
            DeepLinkHistoryScreen()
        }
    }
}

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home_screen")
    object SettingsScreen : Screens("settings_screen")
    object HistoryScreen : Screens("history_screen")
}