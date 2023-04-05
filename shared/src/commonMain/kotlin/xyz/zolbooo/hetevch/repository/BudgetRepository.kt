package xyz.zolbooo.hetevch.repository

interface IBudgetRepository {
    suspend fun getLatest(): Budgets?
    fun setBudget(amount: Long, date: String)
}

class BudgetRepository(
    private val database: Database,
) : IBudgetRepository {
    override suspend fun getLatest(): Budgets? =
        database.budgetQueries.selectLatest().executeAsOneOrNull()

    override fun setBudget(amount: Long, date: String) {
        database.budgetQueries.transaction {
            val budget = database.budgetQueries.selectLatest().executeAsOneOrNull()
            if (budget != null) {
                database.budgetQueries.updateBudget(
                    amount = amount,
                    endDate = TODO("Convert string date to Long"),
                    id = budget.id,
                )
            } else {
                database.budgetQueries.insert(
                    amount = amount,
                    endDate = TODO("Convert string date to Long"),
                )
            }
        }
    }
}
