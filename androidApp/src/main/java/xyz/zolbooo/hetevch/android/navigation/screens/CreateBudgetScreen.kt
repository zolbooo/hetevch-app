package xyz.zolbooo.hetevch.android.navigation.screens

import android.util.Log
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.widgets.BudgetWidget

fun NavGraphBuilder.createBudgetScreen() {
    composable("create-budget") {
        BudgetWidget(
            onSave = { amount, durationInDays ->
                Log.d("BudgetWidget", "Save: $amount, duration is $durationInDays days")
            },
        )
    }
}