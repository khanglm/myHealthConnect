package vn.edu.hust.khanglm.features.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import vn.edu.hust.khanglm.features.home.HomeScreenRoute

@Serializable data object HomeRoute

fun NavController.navigateToHomeScreen(navOptions: NavOptions) = navigate(route = HomeRoute, navOptions)

fun NavGraphBuilder.homeScreen() {
    composable<HomeRoute> {
        HomeScreenRoute()
    }
}