package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.screens.CongratulationsScreen
import xyz.zolbooo.hetevch.usecase.SaveMoneyUseCase

fun NavGraphBuilder.congratulationsScreen(navController: NavController) {
    composable("money-saved") {
        val saveMoneyUseCase = remember { SaveMoneyUseCase() }
        val budget = remember { saveMoneyUseCase.getBudget() }
        val savedAmount = remember { saveMoneyUseCase.getSavedMoneyAmount() }
        val remainingDays = remember { saveMoneyUseCase.getRemainingDaysForBudget(budget) }
        CongratulationsScreen(
            savedAmount = savedAmount,
            estimatedNewBudgetAmount = budget.amount / remainingDays,
            budgetDurationInDays = remainingDays,
            estimatedNewTodayBudgetAmount = budget.dailyAmount + savedAmount,
            onAddToTotalBudgetPress = {
                saveMoneyUseCase.saveMoneyForBudget(savedAmount)
                navController.popBackStack()
                navController.navigate("home")
            },
            onAddToDailyBudgetPress = {
                saveMoneyUseCase.saveMoneyForCurrentDay(savedAmount)
                navController.popBackStack()
                navController.navigate("home")
            },
            onSavePress = {
                saveMoneyUseCase.dismissSavedMoney(savedAmount)
                navController.popBackStack()
                navController.navigate("home")
            },
        )
    }
}
