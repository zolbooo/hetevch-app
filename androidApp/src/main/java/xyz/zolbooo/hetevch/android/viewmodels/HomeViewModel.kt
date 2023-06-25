package xyz.zolbooo.hetevch.android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import xyz.zolbooo.hetevch.usecase.HomeUseCase
import xyz.zolbooo.hetevch.repository.Budget
import xyz.zolbooo.hetevch.usecase.TimeOfDayUseCase

class HomeViewModel : ViewModel() {
    private val homeUseCase = HomeUseCase()
    private val timeOfDayUseCase = TimeOfDayUseCase()

    val timeOfDay = timeOfDayUseCase.getTimeOfDay()

    val budgetFlow = homeUseCase.watchBudget().stateIn(
        scope = viewModelScope,
        initialValue = homeUseCase.getBudget(),
        started = SharingStarted.WhileSubscribed(5000L),
    )
    val expensesFlow = homeUseCase.getExpenses()
        .stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5000L),
        )

    fun getRemainingDaysForBudget(budget: Budget) = homeUseCase.getRemainingDaysForBudget(budget)

    init {
        homeUseCase.updateLastOpenDate()
    }
}
