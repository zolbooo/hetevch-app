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
        database.expenseQueries.deleteAll()
        databaseDriver.close()
    }

    @Test
    fun hasBudget() {
        assertFalse(database.budgetQueries.hasBudget().executeAsOne())
        database.budgetQueries.setBudget(1, 1, 1, 1)
        assertTrue(database.budgetQueries.hasBudget().executeAsOne())
    }

    @Test
    fun setBudget() {
        assertNull(database.budgetQueries.getBudget().executeAsOneOrNull())
        database.budgetQueries.setBudget(1, 1, 1, 1)
        assertNotNull(database.budgetQueries.getBudget().executeAsOneOrNull())
        assertTrue(database.expenseQueries.selectAll().executeAsList().isEmpty())
    }
}
