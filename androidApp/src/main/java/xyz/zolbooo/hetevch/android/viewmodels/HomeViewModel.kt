package xyz.zolbooo.hetevch.android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import xyz.zolbooo.hetevch.helpers.HomeHelper
import xyz.zolbooo.hetevch.repository.Budget

class HomeViewModel : ViewModel() {
    private val homeHelper = HomeHelper()

    val timeOfDay = homeHelper.getTimeOfDay()

    val budgetFlow = homeHelper.watchBudget().stateIn(
        scope = viewModelScope,
        initialValue = homeHelper.getBudget(),
        started = SharingStarted.WhileSubscribed(5000L),
    )
    val expensesFlow = homeHelper.getExpenses()
        .stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5000L),
        )

    fun getRemainingDaysForBudget(budget: Budget) = homeHelper.getRemainingDaysForBudget(budget)

    init {
        homeHelper.updateLastOpenDate()
    }
}
