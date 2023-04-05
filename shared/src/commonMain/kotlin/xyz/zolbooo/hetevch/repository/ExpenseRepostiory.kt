package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

interface IExpenseRepository {
    fun getAll(): Flow<List<Expenses>>
    fun recordExpense(amount: Long)
    fun removeExpense(id: Long)
}

class ExpenseRepository(
    private val database: Database,
) : IExpenseRepository {
    override fun recordExpense(amount: Long) {
        database.expenseQueries.insert(
            amount = amount,
            date = Clock.System.now().epochSeconds,
        )
    }

    override fun getAll(): Flow<List<Expenses>> =
        database.expenseQueries.selectAll()
            .asFlow()
            // I'm not sure if we should use Dispatchers.IO or MainDispatcher,
            // so for now we'll use Dispatchers.IO.
            .mapToList(Dispatchers.IO)

    override fun removeExpense(id: Long) {
        database.expenseQueries.delete(id)
    }
}
