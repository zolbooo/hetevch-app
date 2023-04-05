package xyz.zolbooo.hetevch.di

import org.koin.core.module.Module
import org.koin.dsl.module
import xyz.zolbooo.hetevch.base.coroutines.MainDispatcher
import xyz.zolbooo.hetevch.repository.DriverFactory

actual fun platformModule(): Module = module {
    single { DriverFactory(get()) }
    single { MainDispatcher() }
}
