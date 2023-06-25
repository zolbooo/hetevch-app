package xyz.zolbooo.hetevch.usecase

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

sealed class TimeOfDay {
    object Morning : TimeOfDay()
    object Afternoon : TimeOfDay()
    object Evening : TimeOfDay()
    object Night : TimeOfDay()
}

class TimeOfDayUseCase : KoinComponent {
    private val clock by inject<Clock>()

    fun getTimeOfDay(): TimeOfDay =
        when (clock.now().toLocalDateTime(TimeZone.currentSystemDefault()).hour) {
            in 5 until 12 -> TimeOfDay.Morning
            in 12 until 17 -> TimeOfDay.Afternoon
            in 17 until 21 -> TimeOfDay.Evening
            else -> TimeOfDay.Night
        }
}