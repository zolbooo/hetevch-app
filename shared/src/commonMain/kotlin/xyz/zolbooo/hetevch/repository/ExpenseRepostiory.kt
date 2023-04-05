package xyz.zolbooo.hetevch.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

interface IExpenseRepository {
    fun getAll(): Flow<List<Expenses>>
    fun recordExpense(amount: Long)
    fun removeExpense(id: Long)
}

class ExpenseRepository : KoinComponent, IExpenseRepository {
    private val database: Database by inject()

    override fun recordExpense(amount: Long) {
        database.expenseQueries.insert(
            amount = amount,
            date = Clock.System.now().epochSeconds,
        )
    }

    override fun getAll(): Flow<List<Expenses>> {
        TODO("Not yet implemented")
    }

    override fun removeExpense(id: Long) {
        database.expenseQueries.delete(id)
    }
}
