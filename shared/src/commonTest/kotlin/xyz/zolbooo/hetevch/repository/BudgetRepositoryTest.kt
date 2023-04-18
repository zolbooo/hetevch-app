package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.db.SqlDriver
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import xyz.zolbooo.hetevch.RobolectricTests
import kotlin.test.*
import kotlin.time.Duration.Companion.days

class BudgetRepositoryTest : RobolectricTests() {
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
        database.budgetQueries.deleteAll()
        databaseDriver.close()
    }

    @Test
    fun recordExpense() {
        val now = Instant.parse("2023-01-01T14:00:00.00Z")
        database.budgetQueries.setBudget(
            50_000,
            5_000,
            now.epochSeconds,
            now.plus(10.days).epochSeconds,
        )

        val budgetRepository = BudgetRepository(
            database,
            clock = object : Clock {
                override fun now(): Instant {
                    return now
                }
            },
        )
        budgetRepository.recordExpense(1_000)

        val budget = budgetRepository.getLatest()!!
        assertEquals(49_000, budget.amount)
        assertEquals(5_000, budget.dailyAmount)
        assertEquals(1_000, budget.lastDaySpendings)
    }
}
