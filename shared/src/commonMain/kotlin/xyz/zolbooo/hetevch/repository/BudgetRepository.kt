package xyz.zolbooo.hetevch.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.datetime.*

data class Budget(
    val amount: Long,
    val endTimestamp: Long,
)

interface IBudgetRepository {
    suspend fun getLatest(): Budget?
    suspend fun setBudget(amount: Long, durationInDays: Int)
}

class BudgetRepository(
    private val settings: Settings,
) : IBudgetRepository {
    private val amountKey = "budget-amount"
    private val endDateKey = "budget-end-date"

    override suspend fun getLatest(): Budget? {
        val isBudgetCreated = settings.hasKey(amountKey) && settings.hasKey(endDateKey)
        if (!isBudgetCreated) {
            return null
        }
        return Budget(
            settings.getLong(amountKey, 0),
            settings.getLong(endDateKey, 0)
        )
    }

    override suspend fun setBudget(amount: Long, durationInDays: Int) {
        val timeZone = TimeZone.currentSystemDefault()
        val endDayTimestamp = Clock.System.now()
            .toLocalDateTime(timeZone)
            .date
            .plus(durationInDays, DateTimeUnit.DAY)
            .atStartOfDayIn(timeZone)
            .epochSeconds
        settings[amountKey] = amount
        settings[endDateKey] = endDayTimestamp
    }
}
