package xyz.zolbooo.hetevch.di

import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.Module
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults
import xyz.zolbooo.hetevch.base.coroutines.MainDispatcher
import xyz.zolbooo.hetevch.repository.DriverFactory

actual fun platformModule(): Module = module {
    single { DriverFactory() }
    single<Settings> {
        NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults)
    }
    single { MainDispatcher() }
}
