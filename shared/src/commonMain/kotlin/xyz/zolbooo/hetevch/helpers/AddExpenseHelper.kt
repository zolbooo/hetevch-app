package xyz.zolbooo.hetevch.helpers

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import xyz.zolbooo.hetevch.repository.IExpenseRepository
import kotlin.math.max

class AddExpenseHelper : KoinComponent {
    private val budgetRepository by inject<IBudgetRepository>()
    private val expenseRepository by inject<IExpenseRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun watchBudget() = budgetRepository.watchLatest()

    fun addExpense(budget: Budget, amount: Long) {
        expenseRepository.recordExpense(amount)
        budgetRepository.updateAmount(max(budget.amount - amount, 0))
    }
}
