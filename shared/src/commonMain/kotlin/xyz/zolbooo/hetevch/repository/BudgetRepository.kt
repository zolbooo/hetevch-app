package xyz.zolbooo.hetevch.repository

import com.russhwolf.settings.Settings
import com.russhwolf.settings.set
import kotlinx.datetime.*

data class Budget(
    val amount: Long,
    val endTimestamp: Long,
)

interface IBudgetRepository {
    fun getLatest(): Budget?
    fun setBudget(amount: Long, durationInDays: Int)
    fun updateAmount(newAmount: Long)
}

class BudgetRepository(
    private val settings: Settings,
) : IBudgetRepository {
    private val amountKey = "budget-amount"
    private val endDateKey = "budget-end-date"

    override fun getLatest(): Budget? {
        val isBudgetCreated = settings.hasKey(amountKey) && settings.hasKey(endDateKey)
        if (!isBudgetCreated) {
            return null
        }
        return Budget(
            settings.getLong(amountKey, 0),
            settings.getLong(endDateKey, 0)
        )
    }

    override fun setBudget(amount: Long, durationInDays: Int) {
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

    override fun updateAmount(newAmount: Long) {
        settings[amountKey] = newAmount
    }
}
