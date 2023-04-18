package xyz.zolbooo.hetevch.repository

import com.russhwolf.settings.ExperimentalSettingsApi
import com.russhwolf.settings.Settings
import com.russhwolf.settings.coroutines.FlowSettings
import com.russhwolf.settings.set
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.datetime.*

data class Budget(
    val amount: Long,
    val dailyAmount: Long,
    val end: LocalDate,
)

interface IBudgetRepository {
    fun hasBudget(): Boolean
    fun getLatest(): Budget?
    fun watchLatest(): Flow<Budget>
    fun setBudget(amount: Long, durationInDays: Int)
    fun updateAmount(newAmount: Long)
}

@OptIn(ExperimentalSettingsApi::class)
class BudgetRepository(
    private val settings: Settings,
    private val flowSettings: FlowSettings,
    private val clock: Clock = Clock.System,
) : IBudgetRepository {
    private val amountKey = "budget-amount"
    private val dailyAmountKey = "budget-daily-amount"
    private val endDateKey = "budget-end-date"
    override fun hasBudget(): Boolean =
        listOf(amountKey, endDateKey, dailyAmountKey).all { settings.hasKey(it) }

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

    override fun watchLatest(): Flow<Budget> =
        combine(
            flowSettings.getLongFlow(amountKey, 0),
            flowSettings.getLongFlow(dailyAmountKey, 0),
            flowSettings.getLongFlow(endDateKey, 0).map { endDate ->
                Instant
                    .fromEpochSeconds(endDate)
                    .toLocalDateTime(TimeZone.UTC)
                    .date
            }
        )
        { amount, dailyAmount, endDate ->
            Budget(amount, dailyAmount, endDate)
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
