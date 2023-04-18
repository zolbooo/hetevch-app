package xyz.zolbooo.hetevch.helpers

import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import xyz.zolbooo.hetevch.repository.IExpenseRepository

class BudgetSettingsHelper : KoinComponent {
    private val clock by inject<Clock>()

    private val budgetRepository by inject<IBudgetRepository>()
    private val expenseRepository by inject<IExpenseRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun currentBudgetDuration(budget: Budget) = budget.calculateRemainingDays(clock)

    fun setBudget(amount: Long, duration: Int) {
        budgetRepository.setBudget(amount, duration)
        expenseRepository.deleteAll()
    }
}
