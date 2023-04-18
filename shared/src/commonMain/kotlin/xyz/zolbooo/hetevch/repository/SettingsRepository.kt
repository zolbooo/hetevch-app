package xyz.zolbooo.hetevch.repository

import com.russhwolf.settings.Settings
import kotlinx.datetime.Clock
import kotlinx.datetime.Instant

sealed class Currency {
    object MNT : Currency()
}

interface ISettingsRepository {
    fun getLastOpenDate(): Instant
    fun updateLastOpenDate()
    fun getCurrency(): Currency
    fun setCurrency(currency: Currency)
}

class SettingsRepository(
    private val settings: Settings,
    private val database: Database,
    private val clock: Clock = Clock.System,
) : ISettingsRepository {
    private val currencyKey = "app-currency"

    override fun getLastOpenDate() =
        Instant.fromEpochSeconds(database.budgetQueries.getBudget().executeAsOne().lastUsedAt)

    override fun updateLastOpenDate() =
        database.budgetQueries.updateLastUsedDate(clock.now().epochSeconds)

    override fun getCurrency(): Currency = when (settings.getString(currencyKey, "MNT")) {
        else -> Currency.MNT
    }

    override fun setCurrency(currency: Currency) {
        settings.putString(
            currencyKey,
            when (currency) {
                is Currency.MNT -> "MNT"
            },
        )
    }
}