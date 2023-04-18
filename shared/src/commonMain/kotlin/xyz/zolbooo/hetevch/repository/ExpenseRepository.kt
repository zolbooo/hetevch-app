package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.datetime.Clock

interface IExpenseRepository {
    suspend fun getAll(): List<Expenses>
    fun watchAll(): Flow<List<Expenses>>
    fun recordExpense(amount: Long)
    suspend fun removeExpense(id: Long)
    fun deleteAll()
}

class ExpenseRepository(
    private val database: Database,
    private val clock: Clock = Clock.System,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.IO,
) : IExpenseRepository {
    override fun recordExpense(amount: Long) {
        database.expenseQueries.recordExpense(
            amount = amount,
            date = clock.now().epochSeconds,
        )
    }

    override suspend fun getAll(): List<Expenses> =
        database.expenseQueries.selectAll().executeAsList()

    override fun watchAll(): Flow<List<Expenses>> =
        database.expenseQueries.selectAll()
            .asFlow()
            .mapToList(coroutineDispatcher)

    override suspend fun removeExpense(id: Long) {
        database.expenseQueries.deleteExpense(id)
    }

    override fun deleteAll() {
        database.expenseQueries.deleteAll()
    }
}
