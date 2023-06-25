package xyz.zolbooo.hetevch.android.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import xyz.zolbooo.hetevch.usecase.AddExpenseUseCase

class AddExpenseViewModel : ViewModel() {
    private val addExpenseUseCase = AddExpenseUseCase()

    val budgetFlow = addExpenseUseCase.watchBudget()
        .stateIn(
            scope = viewModelScope,
            initialValue = addExpenseUseCase.getBudget(),
            started = SharingStarted.WhileSubscribed(5000L),
        )

    fun addExpense(amount: Long) = addExpenseUseCase.addExpense(amount)
}