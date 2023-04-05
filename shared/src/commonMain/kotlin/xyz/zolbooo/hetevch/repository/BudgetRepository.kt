package xyz.zolbooo.hetevch.repository

import kotlinx.datetime.*

interface IBudgetRepository {
    suspend fun getLatest(): Budgets?
    suspend fun setBudget(amount: Long, durationInDays: Int)
}

class BudgetRepository(
    private val database: Database,
) : IBudgetRepository {
    override suspend fun getLatest(): Budgets? =
        database.budgetQueries.selectLatest().executeAsOneOrNull()

    override suspend fun setBudget(amount: Long, durationInDays: Int) {
        val timeZone = TimeZone.currentSystemDefault()
        val endDayTimestamp = Clock.System.now()
            .toLocalDateTime(timeZone)
            .date
            .plus(durationInDays, DateTimeUnit.DAY)
            .atStartOfDayIn(timeZone)
            .epochSeconds
        database.budgetQueries.setBudget(
            amount = amount,
            endDate = endDayTimestamp,
        )
    }
}
