package vn.edu.hust.khanglm.myhealthconnect.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import vn.edu.hust.khanglm.features.home.navigation.HomeRoute
import vn.edu.hust.khanglm.features.home.navigation.homeScreen
import vn.edu.hust.khanglm.features.personal.navigation.navigateToPersonalScreen
import vn.edu.hust.khanglm.features.personal.navigation.personalScreen
import vn.edu.hust.khanglm.myhealthconnect.ui.MhcAppState

@Composable
fun MhcNavHost(
    appState: MhcAppState,
    onShowSnackbar: suspend (String, String?) -> Boolean,
    modifier: Modifier = Modifier,
) {
    val navController = appState.navController
    NavHost(
        navController = navController,
        startDestination = HomeRoute,
        modifier = modifier,
    ) {
        homeScreen(
            onUserInfoClicked = { navController.navigateToPersonalScreen() }
        )

        personalScreen {
            navController.popBackStack()
        }
    }
}