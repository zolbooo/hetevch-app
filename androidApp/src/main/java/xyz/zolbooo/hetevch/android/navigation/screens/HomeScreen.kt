package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.screens.HomeScreen
import xyz.zolbooo.hetevch.android.viewmodels.HomeViewModel

fun NavGraphBuilder.homeScreen(navController: NavController) {
    composable("home") {
        val homeViewModel = viewModel<HomeViewModel>()
        val expenses by homeViewModel.expensesFlow.collectAsState()
        HomeScreen(
            currentDailyBudget = homeViewModel.currentDailyBudget,
            budgetGoalAmount = homeViewModel.totalBudget,
            budgetDurationInDays = homeViewModel.budgetDuration,
            expenses = expenses,
            expensesLoading = expenses == null,
            onAddPress = { navController.navigate("add-expense") },
        )
    }
}
