package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.db.SqlDriver
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import kotlinx.datetime.*
import xyz.zolbooo.hetevch.RobolectricTests
import kotlin.test.*

class BudgetRepositoryTest : RobolectricTests() {
    private lateinit var databaseDriver: SqlDriver
    private lateinit var database: Database

    private lateinit var budgetRepository: BudgetRepository

    @BeforeTest
    fun setup() {
        databaseDriver = getTestDriverFactory().createDriver()
        database = Database(databaseDriver)
        budgetRepository = BudgetRepository(database)
    }

    @AfterTest
    fun teardown() {
        database.budgetQueries.deleteAll()
        databaseDriver.close()
    }

    @Test
    fun getBudgetEmpty() = runTest {
        assertNull(budgetRepository.getLatest())
    }

    @Test
    fun setBudget() = runTest {
        val secondsInDay = 60 * 60 * 24
        val currentTimeZone = TimeZone.currentSystemDefault()

        budgetRepository.setBudget(10_000L, 3)
        val todayStartTimestamp = Clock.System.now()
            .toLocalDateTime(currentTimeZone)
            .date.atStartOfDayIn(currentTimeZone)
            .epochSeconds

        val budget = budgetRepository.getLatest()
        assertNotNull(budget)

        assertEquals(10_000L, budget.balance)
        assertEquals(10_000L, budget.amount)
        assertEquals(
            todayStartTimestamp + secondsInDay * 3,
            budget.endDate,
        )
    }
}