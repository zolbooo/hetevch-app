package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.screens.AddExpenseScreen
import xyz.zolbooo.hetevch.android.viewmodels.AddExpenseViewModel

fun NavGraphBuilder.addExpenseScreen(navController: NavController) {
    composable("add-expense") {
        val addExpenseViewModel = viewModel<AddExpenseViewModel>()
        val budget by addExpenseViewModel.budgetFlow.collectAsState()
        AddExpenseScreen(
            balance = budget.amount,
            onAddExpense = { amount ->
                addExpenseViewModel.addExpense(amount)
                navController.popBackStack()
            },
            onBackPress = { navController.popBackStack() },
        )
    }
}
