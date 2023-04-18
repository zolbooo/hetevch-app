package xyz.zolbooo.hetevch.helpers

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import xyz.zolbooo.hetevch.repository.IExpenseRepository

class BudgetSettingsHelper : KoinComponent {
    private val budgetRepository by inject<IBudgetRepository>()
    private val expenseRepository by inject<IExpenseRepository>()

    fun setBudget(amount: Long, duration: Int) {
        budgetRepository.setBudget(amount, duration)
        expenseRepository.deleteAll()
    }
}
