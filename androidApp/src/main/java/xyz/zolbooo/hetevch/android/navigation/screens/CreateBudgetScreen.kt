package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.widgets.BudgetWidget
import xyz.zolbooo.hetevch.helpers.CreateBudgetHelper

fun NavGraphBuilder.createBudgetScreen(navController: NavController) {
    @OptIn(ExperimentalMaterial3Api::class)
    composable("create-budget") {
        val createBudgetHelper = remember { CreateBudgetHelper() }
        Scaffold { paddingValues ->
            BudgetWidget(
                onSave = { amount, durationInDays ->
                    createBudgetHelper.setBudget(amount, durationInDays)
                    navController.popBackStack()
                    navController.navigate("home")
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}