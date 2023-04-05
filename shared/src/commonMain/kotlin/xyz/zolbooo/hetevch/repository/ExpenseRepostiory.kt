package xyz.zolbooo.hetevch.repository

import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class ExpenseRepository : KoinComponent {
    private val database: Database by inject()

    fun recordExpense(amount: Long) {
        database.expenseQueries.insert(
            amount = amount,
            date = Clock.System.now().epochSeconds,
        )
    }
}
