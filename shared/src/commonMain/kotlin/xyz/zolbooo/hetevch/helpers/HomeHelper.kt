package xyz.zolbooo.hetevch.helpers

import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository

class HomeHelper : KoinComponent {
    private val clock by inject<Clock>()

    private val budgetRepository by inject<IBudgetRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun getRemainingDaysForBudget(budget: Budget): Int {
        return TODO("Not implemented")
    }
}
