package xyz.zolbooo.hetevch.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.core.os.bundleOf
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
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
                val navController = rememberNavController()
                val coroutineScope = rememberCoroutineScope()
                DisposableEffect(Unit) {
                    var reportJob: Job? = null
                    val listener = NavController.OnDestinationChangedListener { _, destination, _ ->
                        val route = destination.route ?: return@OnDestinationChangedListener
                        reportJob?.cancel()
                        reportJob = coroutineScope.launch {
                            // Firebase Analytics doesn't record screen view when it was should for less than 1s
                            delay(1000L)
                            Firebase.analytics.logEvent(
                                FirebaseAnalytics.Event.SCREEN_VIEW,
                                bundleOf(FirebaseAnalytics.Param.SCREEN_NAME to route),
                            )
                        }
                    }
                    navController.addOnDestinationChangedListener(listener)
                    onDispose { navController.removeOnDestinationChangedListener(listener) }
                }
                NavigationRoot(navController = navController, startDestination = startDestination)
            }
        }
    }
}
