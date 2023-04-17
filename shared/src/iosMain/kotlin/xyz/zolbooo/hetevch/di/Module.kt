package xyz.zolbooo.hetevch.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.coroutines.toFlowSettings
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults
import xyz.zolbooo.hetevch.base.coroutines.MainDispatcher
import xyz.zolbooo.hetevch.repository.DriverFactory

actual fun platformModule(): Module = module {
    single { DriverFactory() }
    @OptIn(ExperimentalSettingsApi::class)
    single {
        NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults).toFlowSettings()
    }
    single { MainDispatcher() }
}
