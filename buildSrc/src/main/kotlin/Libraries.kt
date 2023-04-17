object Versions {
    const val agp = "7.4.0"
    const val kotlin = "1.8.20"
    const val kotlinCompilerExtensionVersion = "1.4.5"
    const val desugaring = "2.0.3"

    const val coroutines = "1.7.0-Beta"
    const val datetime = "0.4.0"
    const val settings = "1.0.0"

    const val koin = "3.4.0"

    const val sqlDelight = "2.0.0-alpha05"
    const val datastore = "1.0.0"

    const val splashScreen = "1.0.0"

    const val composeBom = "2023.04.00"
    const val activityCompose = "1.7.0"
    const val composeNavigation = "2.5.3"
    const val lifecycleViewModelCompose = "2.6.1"

    const val testKtx = "1.5.0"
    const val robolectric = "4.9"
}

object Libraries {
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"
    const val activityCompose = "androidx.activity:activity-compose:${Versions.activityCompose}"

    object Compose {
        const val bom = "androidx.compose:compose-bom:${Versions.composeBom}"
        const val ui = "androidx.compose.ui:ui"
        const val uiTooling = "androidx.compose.ui:ui-tooling"
        const val uiToolingPreview = "androidx.compose.ui:ui-tooling-preview"
        const val foundation = "androidx.compose.foundation:foundation"
        const val materialIcons = "androidx.compose.material:material-icons-extended"
        const val material3 = "androidx.compose.material3:material3"
        const val material3WindowSizeClass =
            "androidx.compose.material3:material3-window-size-class"
        const val navigation =
            "androidx.navigation:navigation-compose:${Versions.composeNavigation}"
        const val lifecycleViewModelCompose =
            "androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleViewModelCompose}"
    }

    object Common {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"

        const val kotlinxDatetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}"
        const val kotlinxCoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

        const val sqlDelightCoroutinesExtension =
            "app.cash.sqldelight:coroutines-extensions:${Versions.sqlDelight}"

        const val settings = "com.russhwolf:multiplatform-settings:${Versions.settings}"
        const val settingsCoroutines =
            "com.russhwolf:multiplatform-settings-coroutines:${Versions.settings}"
    }

    object UnitTests {
        object Common {
            const val coroutinesTest =
                "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
        }

        object Android {
            const val testKtx = "androidx.test:core-ktx:${Versions.testKtx}"
            const val robolectric = "org.robolectric:robolectric:${Versions.robolectric}"
        }
    }

    object Android {
        const val splashScreen = "androidx.core:core-splashscreen:${Versions.splashScreen}"
        const val desugaring = "com.android.tools:desugar_jdk_libs:${Versions.desugaring}"
        const val sqlDelight = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
        const val datastore = "androidx.datastore:datastore-preferences:${Versions.datastore}"
        const val settingsDatastore =
            "com.russhwolf:multiplatform-settings-datastore:${Versions.settings}"
    }

    object iOS {
        const val sqlDelight = "app.cash.sqldelight:native-driver:${Versions.sqlDelight}"
    }
}
