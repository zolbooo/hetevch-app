package xyz.zolbooo.hetevch.helpers

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.IBudgetRepository

class CreateBudgetHelper : KoinComponent {
    private val budgetRepository by inject<IBudgetRepository>()

    fun setBudget(amount: Long, duration: Int) = budgetRepository.setBudget(amount, duration)
}
