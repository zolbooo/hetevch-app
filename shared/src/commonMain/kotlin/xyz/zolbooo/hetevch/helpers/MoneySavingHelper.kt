package xyz.zolbooo.hetevch.helpers

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.atStartOfDayIn
import kotlinx.datetime.toInstant
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import kotlin.math.ceil
import kotlin.time.DurationUnit

open class MoneySavingHelper : KoinComponent {
    protected val clock by inject<Clock>()

    protected val budgetRepository by inject<IBudgetRepository>()

    fun getBudget() = budgetRepository.getLatest()!!
    fun getRemainingDaysForBudget(budget: Budget): Int = budget.calculateRemainingDays(clock)

    internal fun getSavedMoneyAmount(timeZone: TimeZone): Long {
        val budget = getBudget()

        val daysSinceLastUse = clock.now()
            .toLocalDateTime(timeZone)
            .date
            .atStartOfDayIn(timeZone)
            .minus(budget.lastUsedAt.toInstant(timeZone))
            .toDouble(DurationUnit.DAYS)
            .let { ceil(it) }
            .toLong()
        if (daysSinceLastUse == 0L) {
            return 0
        }

        val multipleDaysSavings = budget.dailyAmount * (daysSinceLastUse - 1)
        val lastDaySavings = budget.dailyAmount - budget.lastDaySpendings
        return multipleDaysSavings + lastDaySavings
    }

    fun getSavedMoneyAmount() = getSavedMoneyAmount(TimeZone.currentSystemDefault())

    fun saveMoneyForCurrentDay(savedAmount: Long) =
        budgetRepository.saveMoneyForCurrentDay(savedAmount)

    fun saveMoneyForBudget(newDailyAmount: Long) =
        budgetRepository.saveMoneyForCurrentDay(newDailyAmount)

    fun dismissSavedMoney(savedAmount: Long) =
        budgetRepository.dismissSavedMoney(savedAmount)
}
