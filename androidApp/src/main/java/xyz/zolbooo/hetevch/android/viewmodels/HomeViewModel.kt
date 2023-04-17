package xyz.zolbooo.hetevch.android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import xyz.zolbooo.hetevch.helpers.HomeHelper

class HomeViewModel : ViewModel() {
    private val homeHelper = HomeHelper()

    private val budget = homeHelper.getBudget()

    val currentDailyBudget = budget.dailyAmount
    val totalBudget = budget.amount
    val budgetDuration = homeHelper.getRemainingDaysForBudget(budget)

    val expensesFlow = homeHelper.getExpenses()
        .stateIn(
            scope = viewModelScope,
            initialValue = null,
            started = SharingStarted.WhileSubscribed(5000L),
        )
}
