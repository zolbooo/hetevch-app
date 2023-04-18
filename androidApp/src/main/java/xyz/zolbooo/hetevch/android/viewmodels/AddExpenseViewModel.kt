package xyz.zolbooo.hetevch.android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import xyz.zolbooo.hetevch.helpers.AddExpenseHelper
import xyz.zolbooo.hetevch.repository.Budget

class AddExpenseViewModel : ViewModel() {
    private val addExpenseHelper = AddExpenseHelper()

    val budgetFlow = addExpenseHelper.watchBudget()
        .stateIn(
            scope = viewModelScope,
            initialValue = addExpenseHelper.getBudget(),
            started = SharingStarted.WhileSubscribed(5000L),
        )

    fun addExpense(budget: Budget, amount: Long) = addExpenseHelper.addExpense(budget, amount)
}