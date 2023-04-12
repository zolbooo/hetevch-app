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
        database.expenseQueries.deleteAll()
        databaseDriver.close()
    }

    @Test
    fun recordExpense() {
        database.expenseQueries.recordExpense(2000L, 1010L)
    }

    @Test
    fun recordExpenseAmountOverflow() {
        database.expenseQueries.recordExpense(15000L, 1010L)
    }

    @Test
    fun deleteExpense() {
        database.expenseQueries.recordExpense(1000L, 1010L)
        database.expenseQueries.recordExpense(2000L, 1020L)
        val expenses = database.expenseQueries.selectAll().executeAsList()
        assertEquals(2000L, expenses[0].amount)
        assertEquals(1000L, expenses[1].amount)

        database.expenseQueries.deleteExpense(expenses[0].id)
    }

    @Test
    fun sumExpensesSince() {
        database.expenseQueries.recordExpense(1000L, 1010L)
        database.expenseQueries.recordExpense(2000L, 1020L)
        database.expenseQueries.recordExpense(2000L, 1030L)
        assertEquals(
            4000L,
            database.expenseQueries.sumExpensesSince(1020L).executeAsOne().SUM,
        )
    }
}
