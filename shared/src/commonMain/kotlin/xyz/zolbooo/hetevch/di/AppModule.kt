package xyz.zolbooo.hetevch.di

import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import xyz.zolbooo.hetevch.repository.*

val appModule = module {
    single { createDatabase(get()) }
}
val repositoryModule = module {
    single<IBudgetRepository> { BudgetRepository(get()) }
    single<IExpenseRepository> { ExpenseRepository(get()) }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        repositoryModule,
        appModule,
        platformModule(),
    )
}
