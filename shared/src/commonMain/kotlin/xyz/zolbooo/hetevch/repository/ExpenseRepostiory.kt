package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
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
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
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
            .mapToList(coroutineDispatcher)

    override suspend fun removeExpense(id: Long) {
        database.expenseQueries.deleteExpense(id)
    }
}
