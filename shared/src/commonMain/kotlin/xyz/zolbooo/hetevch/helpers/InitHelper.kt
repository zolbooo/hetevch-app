package xyz.zolbooo.hetevch.helpers

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import xyz.zolbooo.hetevch.repository.IBudgetRepository
import xyz.zolbooo.hetevch.repository.ISettingsRepository

sealed class BudgetStatus {
    /**
     * There is some money saved from previous days.
     */
    object MoneySaved : BudgetStatus()

    /**
     * Budget is ongoing, just open the home screen.
     */
    object Ongoing : BudgetStatus()

    /**
     * Budget is yet to be created.
     */
    object NotCreated : BudgetStatus()

    /**
     * The budget has ended, user has to create to a new one.
     */
    object Ended : BudgetStatus()
}

class InitHelper : KoinComponent {
    private val clock by inject<Clock>()

    private val budgetRepository by inject<IBudgetRepository>()
    private val settingsRepository by inject<ISettingsRepository>()

    fun getBudgetStatus(): BudgetStatus {
        val budget = budgetRepository.getLatest() ?: return BudgetStatus.NotCreated

        val timezone = TimeZone.currentSystemDefault()
        val today = clock.now().toLocalDateTime(timezone).date
        val lastOpenDate = settingsRepository.getLastOpenDate().toLocalDateTime(timezone).date
        return when {
            budget.end >= today -> BudgetStatus.Ended
            lastOpenDate < today -> BudgetStatus.MoneySaved
            else -> BudgetStatus.Ongoing
        }
    }
}
