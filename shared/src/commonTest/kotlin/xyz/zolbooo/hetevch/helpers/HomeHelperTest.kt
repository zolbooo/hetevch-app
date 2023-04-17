package xyz.zolbooo.hetevch.helpers

import kotlinx.datetime.Clock
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import xyz.zolbooo.hetevch.repository.Budget
import kotlin.test.Test
import kotlin.test.assertEquals

class HomeHelperTest {
    @Test
    fun calculateRemainingDays() {
        val clock = object : Clock {
            override fun now(): Instant {
                return Instant.parse("2023-01-01T14:00:00.00Z")
            }
        }
        assertEquals(
            2,
            Budget(
                amount = 1,
                dailyAmount = 1,
                end = LocalDate.parse("2023-01-03"),
            ).calculateRemainingDays(clock)
        )
    }
}
