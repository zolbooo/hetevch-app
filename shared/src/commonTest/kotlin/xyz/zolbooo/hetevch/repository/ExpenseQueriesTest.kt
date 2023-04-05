package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.db.SqlDriver
import xyz.zolbooo.hetevch.RobolectricTests
import kotlin.test.*

class ExpenseQueriesTest : RobolectricTests() {
    private lateinit var databaseDriver: SqlDriver
    private lateinit var database: Database

    @BeforeTest
    fun setup() {
        databaseDriver = getTestDriverFactory().createDriver()
        database = Database(databaseDriver)
    }

    @AfterTest
    fun teardown() {
        database.budgetQueries.deleteAll()
        database.expenseQueries.deleteAll()
        databaseDriver.close()
    }

    @Test
    fun recordExpense() {
        database.budgetQueries.setBudget(10000L, 1000L)
        val initialBudget = database.budgetQueries.selectLatest().executeAsOne()
        assertEquals(10000L, initialBudget.balance)

        database.expenseQueries.recordExpense(2000L, 1010L)
        val updatedBudget = database.budgetQueries.selectLatest().executeAsOne()
        assertEquals(8000L, updatedBudget.balance)
    }

    @Test
    fun recordExpenseAmountOverflow() {
        database.budgetQueries.setBudget(10000L, 1000L)
        val initialBudget = database.budgetQueries.selectLatest().executeAsOne()
        assertEquals(10000L, initialBudget.balance)

        database.expenseQueries.recordExpense(15000L, 1010L)
        val updatedBudget = database.budgetQueries.selectLatest().executeAsOne()
        assertEquals(0L, updatedBudget.balance)
    }

    @Test
    fun deleteExpense() {
        database.budgetQueries.setBudget(10000L, 1000L)
        val initialBudget = database.budgetQueries.selectLatest().executeAsOne()
        assertEquals(10000L, initialBudget.balance)

        database.expenseQueries.recordExpense(1000L, 1010L)
        database.expenseQueries.recordExpense(2000L, 1020L)
        val expenses = database.expenseQueries.selectAll().executeAsList()
        assertEquals(2000L, expenses[0].amount)
        assertEquals(1000L, expenses[1].amount)

        database.expenseQueries.deleteExpense(expenses[0].id)
        val updatedBudget = database.budgetQueries.selectLatest().executeAsOne()
        assertEquals(9000L, updatedBudget.balance)
    }
}
