package xyz.zolbooo.hetevch.repository

import xyz.zolbooo.hetevch.RobolectricTests
import kotlin.test.*

class BudgetQueriesTest : RobolectricTests() {
    private lateinit var database: Database

    @BeforeTest
    fun setup() {
        database = createDatabase(getTestDriverFactory())
    }

    @AfterTest
    fun teardown() {
        database.budgetQueries.deleteAll()
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
}