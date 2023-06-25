package xyz.zolbooo.hetevch.android.navigation.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.widgets.BudgetWidget
import xyz.zolbooo.hetevch.usecase.CreateBudgetUseCase

fun NavGraphBuilder.createBudgetScreen(navController: NavController) {
    composable("create-budget") {
        val createBudgetUseCase = remember { CreateBudgetUseCase() }
        Scaffold { paddingValues ->
            BudgetWidget(
                onSave = { amount, durationInDays ->
                    createBudgetUseCase.setBudget(amount, durationInDays)
                    navController.popBackStack()
                    navController.navigate("home")
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}