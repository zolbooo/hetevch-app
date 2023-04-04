package xyz.zolbooo.hetevch.di

import org.koin.core.module.Module
import org.koin.dsl.module
import xyz.zolbooo.hetevch.DriverFactory

actual fun platformModule(): Module = module {
    single { DriverFactory() }
}
