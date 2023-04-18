package xyz.zolbooo.hetevch.repository

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToOne
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.datetime.*
import xyz.zolbooo.hetevch.helpers.calculateRemainingDays
import kotlin.math.max

data class Budget(
    val amount: Long,
    val dailyAmount: Long,
    val lastDaySpendings: Long,
    val lastUsedAt: LocalDateTime,
    val end: LocalDate,
)

fun Budgets.asBudget() = Budget(
    amount,
    dailyAmount + previousDaySavings,
    lastDaySpendings,
    Instant.fromEpochSeconds(lastUsedAt).toLocalDateTime(TimeZone.UTC),
    Instant.fromEpochSeconds(endDate).toLocalDateTime(TimeZone.UTC).date,
)

interface IBudgetRepository {
    fun hasBudget(): Boolean
    fun getLatest(): Budget?
    fun watchLatest(): Flow<Budget>
    fun setBudget(amount: Long, durationInDays: Int)
    fun recordExpense(amount: Long)
}

class BudgetRepository(
    private val database: Database,
    private val clock: Clock = Clock.System,
    private val coroutineDispatcher: CoroutineDispatcher = Dispatchers.Default,
) : IBudgetRepository {
    override fun hasBudget(): Boolean = database.budgetQueries.hasBudget().executeAsOne()

    override fun getLatest(): Budget? =
        database.budgetQueries.getBudget().executeAsOneOrNull()?.asBudget()

    override fun watchLatest(): Flow<Budget> =
        database.budgetQueries.getBudget().asFlow().mapToOne(coroutineDispatcher)
            .map { it.asBudget() }

    override fun setBudget(amount: Long, durationInDays: Int) {
        val now = clock.now()
        val endDayTimestamp = now.toLocalDateTime(TimeZone.currentSystemDefault()).date.plus(
            durationInDays,
            DateTimeUnit.DAY
        ).atStartOfDayIn(TimeZone.UTC).epochSeconds
        database.budgetQueries.setBudget(
            amount = amount,
            dailyAmount = amount / durationInDays,
            currentDate = now.epochSeconds,
            endDate = endDayTimestamp,
        )
    }

    override fun recordExpense(amount: Long) {
        val date = clock.now().epochSeconds
        database.transaction {
            database.expenseQueries.recordExpense(amount, date)
            val budget = database.budgetQueries.getBudget().executeAsOne()
            database.budgetQueries.updateBudget(
                expenseAmount = amount,
                dailyAmount = max(
                    if (budget.dailyAmount + amount > budget.dailyAmount) {
                        (budget.amount - amount) / budget.asBudget().calculateRemainingDays(clock)
                    } else budget.dailyAmount, 0
                ),
                date = date,
            )
        }
    }
}
