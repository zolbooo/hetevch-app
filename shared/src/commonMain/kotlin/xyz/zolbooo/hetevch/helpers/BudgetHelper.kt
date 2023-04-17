package xyz.zolbooo.hetevch.helpers

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.BudgetRepository

class CreateBudgetHelper : KoinComponent {
    private val budgetRepository by inject<BudgetRepository>()

    fun setBudget(amount: Long, duration: Int) = budgetRepository.setBudget(amount, duration)
}
