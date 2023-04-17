package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import xyz.zolbooo.hetevch.RobolectricTests
import kotlin.test.*

class ExpenseRepositoryTest : RobolectricTests() {
    private lateinit var databaseDriver: SqlDriver
    private lateinit var database: Database

    private lateinit var expenseRepository: ExpenseRepository

    @BeforeTest
    fun setup() {
        databaseDriver = getTestDriverFactory().createDriver()
        database = Database(databaseDriver)

        expenseRepository = ExpenseRepository(database)
    }

    @AfterTest
    fun teardown() {
        database.expenseQueries.deleteAll()
        databaseDriver.close()
    }

    @Test
    fun recordExpense() = runTest {
        expenseRepository.recordExpense(5_000)
    }

    @Test
    fun removeExpense() = runTest {
        expenseRepository.recordExpense(5_000)
        expenseRepository.recordExpense(4_000)

        val expenses = expenseRepository.getAll()
        expenseRepository.removeExpense(expenses[1].id)

        val updatedExpenses = expenseRepository.getAll()
        assertEquals(updatedExpenses[0].amount, 4_000)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun watchAllExpenses() = runTest {
        val testExpenseRepository = ExpenseRepository(
            database,
            coroutineDispatcher = UnconfinedTestDispatcher(testScheduler),
        )

        val expenses = mutableListOf<List<Expenses>>()
        backgroundScope.launch {
            testExpenseRepository.watchAll().toList(expenses)
        }
        // Add delays to allow Flow to write intermediate values to the list
        testExpenseRepository.recordExpense(500)
        delay(500)
        testExpenseRepository.recordExpense(1000)
        delay(500)

        assertEquals(2, expenses.size)
        assertEquals(500, expenses[0][0].amount)

        assertEquals(500, expenses[1][1].amount)
        assertEquals(1000, expenses[1][0].amount)
    }
}
