package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

interface IExpenseRepository {
    suspend fun getAll(): Flow<List<Expenses>>
    suspend fun recordExpense(amount: Long)
    suspend fun removeExpense(id: Long)
}

class ExpenseRepository(
    private val database: Database,
) : IExpenseRepository {
    override suspend fun recordExpense(amount: Long) {
        database.expenseQueries.recordExpense(
            amount = amount,
            date = Clock.System.now().epochSeconds,
        )
    }

    override suspend fun getAll(): Flow<List<Expenses>> =
        database.expenseQueries.selectAll()
            .asFlow()
            // I'm not sure if we should use Dispatchers.IO or MainDispatcher,
            // so for now we'll use Dispatchers.IO.
            .mapToList(Dispatchers.IO)

    override suspend fun removeExpense(id: Long) {
        database.expenseQueries.deleteExpense(id)
    }
}
