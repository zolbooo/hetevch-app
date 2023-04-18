package xyz.zolbooo.hetevch.helpers

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.IBudgetRepository

class AddExpenseHelper : KoinComponent {
    private val budgetRepository by inject<IBudgetRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun watchBudget() = budgetRepository.watchLatest()

    fun addExpense(amount: Long) = budgetRepository.recordExpense(amount)
}
