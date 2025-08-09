package vn.edu.hust.khanglm.features.personal.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import kotlinx.serialization.Serializable
import vn.edu.hust.khanglm.features.personal.PersonalRoute

@Serializable
data object PersonalRoute

fun NavController.navigateToPersonalScreen(navOptions: NavOptions? = null) = navigate(route = PersonalRoute, navOptions)

fun NavGraphBuilder.personalScreen(
    goBack: () -> Unit
) {
    composable<PersonalRoute> {
        PersonalRoute(
            goBack = goBack
        )
    }
}