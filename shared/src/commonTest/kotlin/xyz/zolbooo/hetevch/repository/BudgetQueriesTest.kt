package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.db.SqlDriver
import xyz.zolbooo.hetevch.RobolectricTests
import kotlin.test.*

class BudgetQueriesTest : RobolectricTests() {
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
        databaseDriver.close()
    }

    @Test
    fun insertAndGetLatest() {
        val amount = 1000L
        val timestamp = 1000L
        database.budgetQueries.insert(amount, timestamp)

        val budget = database.budgetQueries.selectLatest().executeAsOneOrNull()
        assertNotNull(budget, "Expected budget to be inserted")
        assertEquals(amount, budget.amount)
        assertEquals(amount, budget.balance)
        assertEquals(timestamp, budget.endDate)
    }

    @Test
    fun insertAndUpdateBudget() {
        val amount = 1000L
        val timestamp = 1000L
        database.budgetQueries.insert(amount, timestamp)

        var budget = database.budgetQueries.selectLatest().executeAsOneOrNull()
        assertNotNull(budget, "Expected budget to be inserted")

        val newAmount = 10000L
        val newTimestamp = 10000L
        database.budgetQueries.updateBudget(
            amount = newAmount,
            endDate = newTimestamp,
            id = budget.id,
        )

        budget = database.budgetQueries.selectLatest().executeAsOneOrNull()
        assertNotNull(budget, "Expected budget to be exist")
        assertEquals(newAmount, budget.amount)
        assertEquals(newAmount, budget.balance)
    }
}