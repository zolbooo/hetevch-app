package xyz.zolbooo.hetevch.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module
import xyz.zolbooo.hetevch.repository.*

val appModule = module {
    single {
        val driverFactory = get<DriverFactory>()
        Database(driverFactory.createDriver())
    }
}
val repositoryModule = module {
    single<IBudgetRepository> { BudgetRepository(get()) }
    single<IExpenseRepository> { ExpenseRepository(get()) }
}
val dispatcherModule = module {
    factory { Dispatchers.Default }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        repositoryModule,
        appModule,
        dispatcherModule,
        platformModule(),
    )
}
