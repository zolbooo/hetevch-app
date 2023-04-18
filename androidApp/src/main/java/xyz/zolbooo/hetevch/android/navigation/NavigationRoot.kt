package xyz.zolbooo.hetevch.android.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import xyz.zolbooo.hetevch.android.navigation.screens.addExpenseScreen
import xyz.zolbooo.hetevch.android.navigation.screens.createBudgetScreen
import xyz.zolbooo.hetevch.android.navigation.screens.homeScreen

@Composable
fun NavigationRoot(startDestination: String) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        createBudgetScreen(navController)
        homeScreen(navController)
        addExpenseScreen(navController)
    }
}
