package xyz.zolbooo.hetevch.di

import android.content.Context
import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.ExperimentalSettingsImplementation
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.datastore.DataStoreSettings
import org.koin.core.module.Module
import org.koin.dsl.module
import xyz.zolbooo.hetevch.base.coroutines.MainDispatcher
import xyz.zolbooo.hetevch.repository.DriverFactory
import xyz.zolbooo.hetevch.repository.dataStore

actual fun platformModule(): Module = module {
    single { DriverFactory(get()) }
    @OptIn(ExperimentalSettingsApi::class)
    single<FlowSettings> {
        @OptIn(ExperimentalSettingsImplementation::class)
        DataStoreSettings(get<Context>().dataStore)
    }
    single { MainDispatcher() }
}
