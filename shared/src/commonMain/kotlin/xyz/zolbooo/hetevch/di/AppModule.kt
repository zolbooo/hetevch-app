package xyz.zolbooo.hetevch.di

import org.koin.core.context.startKoin
import org.koin.dsl.module
import xyz.zolbooo.hetevch.createDatabase

val appModule = module {
    single { createDatabase(get()) }
}

fun initKoin() = startKoin {
    modules(
        appModule,
        platformModule(),
    )
}
