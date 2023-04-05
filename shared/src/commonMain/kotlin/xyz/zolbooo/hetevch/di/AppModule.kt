package xyz.zolbooo.hetevch.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import xyz.zolbooo.hetevch.repository.createDatabase

val appModule = module {
    single { createDatabase(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        appModule,
        platformModule(),
    )
}
