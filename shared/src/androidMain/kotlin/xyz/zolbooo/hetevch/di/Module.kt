package xyz.zolbooo.hetevch.di

import android.content.Context
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.core.module.Module
import org.koin.dsl.module
import xyz.zolbooo.hetevch.base.coroutines.MainDispatcher
import xyz.zolbooo.hetevch.repository.DriverFactory

actual fun platformModule(): Module = module {
    single { DriverFactory(get()) }
    single<Settings> {
        val sharedPreferences = get<Context>().getSharedPreferences(
            "xyz.zolbooo.hetevch.SETTINGS",
            Context.MODE_PRIVATE
        )
        SharedPreferencesSettings(sharedPreferences)
    }
    single { MainDispatcher() }
}
