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
        TODO("Not yet implemented")
    }
}
