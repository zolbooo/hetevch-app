package xyz.zolbooo.hetevch.android.navigation.screens

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import xyz.zolbooo.hetevch.android.ui.widgets.BudgetWidget

fun NavGraphBuilder.createBudgetScreen() {
    @OptIn(ExperimentalMaterial3Api::class)
    composable("create-budget") {
        Scaffold { paddingValues ->
            BudgetWidget(
                onSave = { amount, durationInDays ->
                    Log.d("BudgetWidget", "Save: $amount, duration is $durationInDays days")
                },
                modifier = Modifier.padding(paddingValues)
            )
        }
    }
}