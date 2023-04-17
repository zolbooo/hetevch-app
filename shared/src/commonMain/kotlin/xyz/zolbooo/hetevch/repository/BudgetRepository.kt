package xyz.zolbooo.hetevch.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.datetime.*

data class Budget(
    val amount: Long,
    val dailyAmount: Long,
    val end: LocalDate,
)

interface IBudgetRepository {
    fun hasBudget(): Boolean
    fun getLatest(): Budget?
    fun setBudget(amount: Long, durationInDays: Int)
    fun updateAmount(newAmount: Long)
}

class BudgetRepository(
    private val settings: Settings,
    private val clock: Clock = Clock.System,
) : IBudgetRepository {
    private val amountKey = "budget-amount"
    private val dailyAmountKey = "budget-daily-amount"
    private val endDateKey = "budget-end-date"
    override fun hasBudget(): Boolean =
        settings.hasKey(amountKey) && settings.hasKey(endDateKey) && settings.hasKey(dailyAmountKey)

    override fun getLatest(): Budget? {
        if (!hasBudget()) {
            return null
        }
        return Budget(
            settings.getLong(amountKey, 0),
            settings.getLong(dailyAmountKey, 0),
            Instant
                .fromEpochSeconds(settings.getLong(endDateKey, 0))
                .toLocalDateTime(TimeZone.UTC)
                .date,
        )
    }

    override fun setBudget(amount: Long, durationInDays: Int) {
        val timeZone = TimeZone.currentSystemDefault()
        val endDayTimestamp = clock.now()
            .toLocalDateTime(timeZone)
            .date
            .plus(durationInDays, DateTimeUnit.DAY)
            .atStartOfDayIn(TimeZone.UTC)
            .epochSeconds
        settings[amountKey] = amount
        settings[dailyAmountKey] = amount / durationInDays
        settings[endDateKey] = endDayTimestamp
    }

    override fun updateAmount(newAmount: Long) {
        settings[amountKey] = newAmount
    }
}
