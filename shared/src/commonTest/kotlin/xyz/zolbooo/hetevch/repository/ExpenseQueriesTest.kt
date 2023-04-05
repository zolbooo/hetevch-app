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
    fun insertAndGetLatest() {
        database.transaction {
            repeat(3) {
                database.expenseQueries.insert(1000L * (it + 1), it.toLong())
            }
        }

        val latestExpenses = database.expenseQueries.selectAll().executeAsList()
        assertEquals(3, latestExpenses.size)
        // Expenses must be sorted by date in descending order
        assertEquals(3000, latestExpenses[0].amount)
        assertEquals(2000, latestExpenses[1].amount)
        assertEquals(1000, latestExpenses[2].amount)
    }
}
