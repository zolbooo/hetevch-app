package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.compose.runtime.remember
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.screens.CongratulationsScreen
import xyz.zolbooo.hetevch.helpers.MoneySavingHelper

fun NavGraphBuilder.congratulationsScreen(navController: NavController) {
    composable("money-saved") {
        val moneySavingHelper = remember { MoneySavingHelper() }
        val budget = remember { moneySavingHelper.getBudget() }
        val savedAmount = remember { moneySavingHelper.getSavedMoneyAmount() }
        val remainingDays = remember { moneySavingHelper.getRemainingDaysForBudget(budget) }
        CongratulationsScreen(
            savedAmount = savedAmount,
            estimatedNewBudgetAmount = budget.amount / remainingDays,
            budgetDurationInDays = remainingDays,
            estimatedNewTodayBudgetAmount = budget.dailyAmount + savedAmount,
            onAddToTotalBudgetPress = {
                moneySavingHelper.saveMoneyForBudget(savedAmount)
                navController.popBackStack()
                navController.navigate("home")
            },
            onAddToDailyBudgetPress = {
                moneySavingHelper.saveMoneyForCurrentDay(savedAmount)
                navController.popBackStack()
                navController.navigate("home")
            },
            onSavePress = {
                moneySavingHelper.dismissSavedMoney(savedAmount)
                navController.popBackStack()
                navController.navigate("home")
            },
        )
    }
}
