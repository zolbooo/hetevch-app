package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.components.BottomBar
import xyz.zolbooo.hetevch.android.ui.widgets.BudgetWidget
import xyz.zolbooo.hetevch.helpers.BudgetSettingsHelper

@OptIn(ExperimentalMaterial3Api::class)
fun NavGraphBuilder.budgetSettingsScreen(navController: NavController) {
    composable("budget-settings") {
        val budgetSettingsHelper = remember { BudgetSettingsHelper() }
        // TODO: Provide default values for the screen
        Scaffold(bottomBar = {
            BottomBar(
                onHomePress = {
                    navController.popBackStack(
                        route = "home",
                        inclusive = false,
                        saveState = false,
                    )
                },
                onBudgetPress = {},
                onAddExpensePress = { navController.navigate("add-expense") },
            )
        }) { paddingValues ->
            Surface(Modifier.padding(paddingValues)) {
                BudgetWidget(onSave = budgetSettingsHelper::setBudget)
            }
        }
    }
}
