package xyz.zolbooo.hetevch.usecase

import kotlinx.datetime.*
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import xyz.zolbooo.hetevch.repository.IExpenseRepository
import xyz.zolbooo.hetevch.repository.ISettingsRepository

class HomeUseCase : KoinComponent {
    private val clock by inject<Clock>()

    private val budgetRepository by inject<IBudgetRepository>()
    private val settingsRepository by inject<ISettingsRepository>()
    private val expensesRepository by inject<IExpenseRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun watchBudget() = budgetRepository.watchLatest()
    fun getRemainingDaysForBudget(budget: Budget): Int = budget.calculateRemainingDays(clock)

    fun getExpenses() = expensesRepository.watchAll()

    fun updateLastOpenDate() = settingsRepository.updateLastOpenDate()
}
