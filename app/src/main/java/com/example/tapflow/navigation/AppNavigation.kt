package com.example.tapflow.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.tapflowfeature_nfc.screens.ConfigureTagScreen
import com.tapflowfeature_nfc.screens.NfcScreen

@Composable
fun AppNavigation() {

    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = AppRoute.Nfc.route
    ) {

        composable(AppRoute.Nfc.route) {

            NfcScreen(
                onNavigateToTagConfig = { uid ->
                    navController.navigate(
                        AppRoute.ConfigureTag.create(uid)
                    )
                }
            )
        }

        composable(
            route = AppRoute.ConfigureTag.route
        ) { backStackEntry ->

            val uid =
                backStackEntry
                    .arguments
                    ?.getString("uid")
                    ?: ""

            ConfigureTagScreen(uid)
        }
    }
}