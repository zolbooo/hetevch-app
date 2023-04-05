package xyz.zolbooo.hetevch.repository

import org.koin.core.component.KoinComponent

interface IBudgetRepository {
    suspend fun getLatest(): Budgets
    fun setBudget(amount: Long, date: String)
}

class BudgetRepository : KoinComponent, IBudgetRepository {
    override suspend fun getLatest(): Budgets {
        TODO("Not yet implemented")
    }

    override fun setBudget(amount: Long, date: String) {
        TODO("Not yet implemented")
    }
}
