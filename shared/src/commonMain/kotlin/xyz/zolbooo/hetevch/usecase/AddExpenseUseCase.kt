package xyz.zolbooo.hetevch.usecase

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.IBudgetRepository

class AddExpenseUseCase : KoinComponent {
    private val budgetRepository by inject<IBudgetRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun watchBudget() = budgetRepository.watchLatest()

    fun addExpense(amount: Long) = budgetRepository.recordExpense(amount)
}
