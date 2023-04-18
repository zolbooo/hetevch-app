package xyz.zolbooo.hetevch.helpers

import kotlinx.datetime.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import xyz.zolbooo.hetevch.repository.IExpenseRepository
import xyz.zolbooo.hetevch.repository.ISettingsRepository

fun Budget.calculateRemainingDays(clock: Clock): Int {
    val today = clock.now().toLocalDateTime(TimeZone.UTC).date
    return end.minus(today).days
}

sealed class TimeOfDay {
    object Morning : TimeOfDay()
    object Afternoon : TimeOfDay()
    object Evening : TimeOfDay()
    object Night : TimeOfDay()
}

class HomeHelper : KoinComponent {
    private val clock by inject<Clock>()

    private val budgetRepository by inject<IBudgetRepository>()
    private val settingsRepository by inject<ISettingsRepository>()
    private val expensesRepository by inject<IExpenseRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun watchBudget() = budgetRepository.watchLatest()
    fun getRemainingDaysForBudget(budget: Budget): Int = budget.calculateRemainingDays(clock)

    fun getExpenses() = expensesRepository.watchAll()

    fun updateLastOpenDate() = settingsRepository.updateLastOpenDate()

    fun getTimeOfDay(): TimeOfDay =
        when (clock.now().toLocalDateTime(TimeZone.currentSystemDefault()).hour) {
            in 5 until 12 -> TimeOfDay.Morning
            in 12 until 17 -> TimeOfDay.Afternoon
            in 17 until 21 -> TimeOfDay.Evening
            else -> TimeOfDay.Night
        }
}
