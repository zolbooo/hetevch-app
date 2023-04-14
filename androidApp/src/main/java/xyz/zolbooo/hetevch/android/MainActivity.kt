package xyz.zolbooo.hetevch.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.android.navigation.NavigationRoot
import xyz.zolbooo.hetevch.helpers.BudgetStatus
import xyz.zolbooo.hetevch.helpers.InitHelper

class MainActivity : ComponentActivity() {
    private val initHelper = InitHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        // Compute value for startDestination here as the splash screen is being shown
        val startDestination = when (initHelper.getBudgetStatus()) {
            BudgetStatus.NotCreated -> "create-budget"
            BudgetStatus.MoneySaved -> "money-saved"
            BudgetStatus.Ongoing -> "home"
            BudgetStatus.Ended -> "budget-ended"
        }
        setContent {
            HetevchTheme {
                NavigationRoot(startDestination = startDestination)
            }
        }
    }
}
