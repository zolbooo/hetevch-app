package xyz.zolbooo.hetevch.android.navigation

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import xyz.zolbooo.hetevch.android.navigation.screens.addExpenseScreen
import xyz.zolbooo.hetevch.android.navigation.screens.budgetSettingsScreen
import xyz.zolbooo.hetevch.android.navigation.screens.saveMoneyScreen
import xyz.zolbooo.hetevch.android.navigation.screens.createBudgetScreen
import xyz.zolbooo.hetevch.android.navigation.screens.homeScreen

@Composable
fun NavigationRoot(navController: NavHostController, startDestination: String) {
    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(
        color = MaterialTheme.colorScheme.background
    )
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        createBudgetScreen(navController)
        homeScreen(navController)
        saveMoneyScreen(navController)
        addExpenseScreen(navController)
        budgetSettingsScreen(navController)
    }
}
