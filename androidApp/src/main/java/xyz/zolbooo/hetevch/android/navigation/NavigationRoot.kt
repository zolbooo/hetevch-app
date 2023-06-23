package xyz.zolbooo.hetevch.android.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import xyz.zolbooo.hetevch.android.navigation.screens.addExpenseScreen
import xyz.zolbooo.hetevch.android.navigation.screens.budgetSettingsScreen
import xyz.zolbooo.hetevch.android.navigation.screens.congratulationsScreen
import xyz.zolbooo.hetevch.android.navigation.screens.createBudgetScreen
import xyz.zolbooo.hetevch.android.navigation.screens.homeScreen

@Composable
fun NavigationRoot(startDestination: String) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.background
    )
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        createBudgetScreen(navController)
        homeScreen(navController)
        congratulationsScreen(navController)
        addExpenseScreen(navController)
        budgetSettingsScreen(navController)
    }
}
