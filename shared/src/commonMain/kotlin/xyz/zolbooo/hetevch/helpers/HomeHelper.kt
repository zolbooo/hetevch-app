package xyz.zolbooo.hetevch.helpers

import kotlinx.datetime.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository

fun Budget.calculateRemainingDays(clock: Clock): Int {
    val today = clock.now().toLocalDateTime(TimeZone.UTC).date
    return end.minus(today).days + 1
}

class HomeHelper : KoinComponent {
    private val clock by inject<Clock>()

    private val budgetRepository by inject<IBudgetRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun getRemainingDaysForBudget(budget: Budget): Int = budget.calculateRemainingDays(clock)
}
