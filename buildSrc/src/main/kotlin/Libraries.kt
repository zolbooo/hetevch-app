object Versions {
    const val agp = "7.4.0"
    const val kotlin = "1.8.10"
    const val kotlinCompilerExtensionVersion = "1.4.4"

    const val coroutines = "1.7.0-Beta"
    const val datetime = "0.4.0"

    const val koin = "3.4.0"

    const val sqlDelight = "2.0.0-alpha05"
    const val composeBom = "2023.03.00"

    const val activityCompose = "1.7.0"
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
        const val material = "androidx.compose.material:material"
    }

    object Common {
        const val koinCore = "io.insert-koin:koin-core:${Versions.koin}"

        const val kotlinxDatetime = "org.jetbrains.kotlinx:kotlinx-datetime:${Versions.datetime}"
        const val kotlinxCoroutinesCore =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"

        const val sqlDelightCoroutinesExtension =
            "app.cash.sqldelight:coroutines-extensions:${Versions.sqlDelight}"
    }

    object Android {
        const val sqlDelight = "app.cash.sqldelight:android-driver:${Versions.sqlDelight}"
    }

    object iOS {
        const val sqlDelight = "app.cash.sqldelight:native-driver:${Versions.sqlDelight}"
    }
}
