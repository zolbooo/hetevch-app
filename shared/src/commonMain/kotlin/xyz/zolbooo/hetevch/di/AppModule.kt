package xyz.zolbooo.hetevch.di

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.coroutines.toBlockingSettings
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.Clock
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
@OptIn(ExperimentalSettingsApi::class)
val repositoryModule = module {
    single {
        val flowSettings = get<FlowSettings>()
        flowSettings.toBlockingSettings()
    }
    single<IBudgetRepository> { BudgetRepository(get(), get()) }
    single<IExpenseRepository> { ExpenseRepository(get()) }
    single<ISettingsRepository> { SettingsRepository(get()) }
}
val dispatcherModule = module {
    factory { Dispatchers.Default }
}
val clockModule = module {
    single<Clock> { Clock.System }
}

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(
        repositoryModule,
        appModule,
        dispatcherModule,
        clockModule,
        platformModule(),
    )
}
