package xyz.zolbooo.hetevch.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.android.navigation.NavigationRoot

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            HetevchTheme {
                NavigationRoot(startDestination = "home")
            }
        }
    }
}
