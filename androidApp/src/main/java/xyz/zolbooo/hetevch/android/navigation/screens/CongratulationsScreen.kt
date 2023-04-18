package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.screens.CongratulationsScreen

fun NavGraphBuilder.congratulationsScreen(navController: NavController) {
    composable("money-saved") {
        CongratulationsScreen(
            savedAmount = 0,
            estimatedNewBudgetAmount = 0,
            budgetDurationInDays = 0,
            estimatedNewTodayBudgetAmount = 0,
            onAddToTotalBudgetPress = { /*TODO*/ },
            onAddToDailyBudgetPress = { /*TODO*/ },
            onSavePress = {
                navController.popBackStack()
                navController.navigate("home")
            },
        )
    }
}
