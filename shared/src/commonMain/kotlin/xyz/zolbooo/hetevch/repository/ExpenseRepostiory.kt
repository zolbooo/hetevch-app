package xyz.zolbooo.hetevch.repository

import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface IExpenseRepository {
    fun recordExpense(amount: Long)
}

class ExpenseRepository : KoinComponent, IExpenseRepository {
    private val database: Database by inject()

    override fun recordExpense(amount: Long) {
        database.expenseQueries.insert(
            amount = amount,
            date = Clock.System.now().epochSeconds,
        )
    }
}
