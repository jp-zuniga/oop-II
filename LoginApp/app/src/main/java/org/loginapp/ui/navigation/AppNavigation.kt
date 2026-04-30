package org.loginapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import org.loginapp.ui.screens.DetailView
import org.loginapp.ui.screens.HomeView
import org.loginapp.ui.theme.LoginAppTheme

@Composable
fun AppNavigation() {
    val controller = rememberNavController()

    NavHost(
        navController = controller,
        startDestination = HomeScreen,
    ) {
        composable<HomeScreen> {
            HomeView(
                onNavigationToDetail = {
                    id -> controller.navigate(DetailScreen(id))
                }
            )
        }

        composable<DetailScreen> {
            backStackEntry ->
            val args = backStackEntry.toRoute<DetailScreen>()
            DetailView(
                userId = args.userId,
                onBack = { controller.popBackStack() }
            )
        }
    }
}

@Composable
@Preview
fun AppNavigationPreview() {
    LoginAppTheme { AppNavigation() }
}
