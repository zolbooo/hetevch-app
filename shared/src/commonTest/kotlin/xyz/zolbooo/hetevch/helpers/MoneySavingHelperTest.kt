package xyz.zolbooo.hetevch.helpers

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import kotlin.test.AfterTest
import kotlin.test.Test
import kotlin.test.assertEquals

fun Clock.Companion.createMockInstance(instant: Instant) = object : Clock {
    override fun now(): Instant = instant
}

fun createReadonlyBudgetRepository(budget: Budget) = object : IBudgetRepository {
    override fun hasBudget(): Boolean = true

    override fun getLatest(): Budget = budget

    override fun watchLatest(): Flow<Budget> = flowOf(budget)

    override fun setBudget(amount: Long, durationInDays: Int) {
        throw UnsupportedOperationException("Read-only instance")
    }

    override fun recordExpense(amount: Long) {
        throw UnsupportedOperationException("Read-only instance")
    }
}

class MoneySavingHelperTest {
    @AfterTest
    fun teardown() {
        stopKoin()
    }

    private val mockBudget = Budget(
        amount = 9_500,
        dailyAmount = 1_000,
        lastDaySpendings = 500,
        lastUsedAt = LocalDateTime.parse("2023-01-01T13:59:04"),
        end = LocalDate.parse("2023-01-10"),
    )
    private val mockBudgetRepository = createReadonlyBudgetRepository(mockBudget)

    @Test
    fun savedMoneySinceYesterday() {
        val currentTime = Instant.parse("2023-01-02T12:00:00Z")
        val mockClock = Clock.createMockInstance(currentTime)

        startKoin {
            modules(
                module {
                    single { mockClock }
                    single { mockBudgetRepository }
                }
            )
        }

        val moneySavingHelper = MoneySavingHelper()
        assertEquals(500L, moneySavingHelper.getSavedMoneyAmount(TimeZone.UTC))
    }

    @Test
    fun savedMoneyMultipleDays() {
        val currentTime = Instant.parse("2023-01-04T19:00:00Z")
        val mockClock = Clock.createMockInstance(currentTime)

        startKoin {
            modules(
                module {
                    single { mockClock }
                    single { mockBudgetRepository }
                }
            )
        }

        val moneySavingHelper = MoneySavingHelper()
        assertEquals(2500L, moneySavingHelper.getSavedMoneyAmount(TimeZone.UTC))
    }

    // Updated dailyAmount would be (10000 - 1500) / 8 = 937
    private val mockBudgetOverused = Budget(
        amount = 10_000,
        dailyAmount = 1_000,
        lastDaySpendings = 1_500,
        lastUsedAt = LocalDateTime.parse("2023-01-01T13:59:04"),
        end = LocalDate.parse("2023-01-10"),
    )
    private val mockBudgetRepositoryOverused = createReadonlyBudgetRepository(mockBudgetOverused)

    @Test
    fun savedMoneyYesterdayOverused() {
        val currentTime = Instant.parse("2023-01-02T19:00:00Z")
        val mockClock = Clock.createMockInstance(currentTime)

        startKoin {
            modules(
                module {
                    single { mockClock }
                    single { mockBudgetRepositoryOverused }
                }
            )
        }

        val moneySavingHelper = MoneySavingHelper()
        assertEquals(-500, moneySavingHelper.getSavedMoneyAmount(TimeZone.UTC))
    }
    @Test
    fun savedMoneyMultipleDaysOverused() {
        val currentTime = Instant.parse("2023-01-03T21:49:00Z")
        val mockClock = Clock.createMockInstance(currentTime)

        startKoin {
            modules(
                module {
                    single { mockClock }
                    single { mockBudgetRepositoryOverused }
                }
            )
        }

        val moneySavingHelper = MoneySavingHelper()
        assertEquals(500, moneySavingHelper.getSavedMoneyAmount(TimeZone.UTC))
    }
}
