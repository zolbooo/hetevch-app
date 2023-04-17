package xyz.zolbooo.hetevch.android.ui.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.android.utils.formatMNT
import xyz.zolbooo.hetevch.repository.Expenses

fun LazyListScope.expenseList(loading: Boolean, expenses: List<Expenses>?) {
    if (loading) {
        return item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                CircularProgressIndicator()
            }
        }
    }
    checkNotNull(expenses)
    if (expenses.isEmpty()) {
        return item {
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    text = stringResource(R.string.no_expenses),
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                )
            }
        }
    }
    items(items = expenses, key = { it.id }) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(bottom = 5.dp)
        ) {
            Column(Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
                Text(
                    text = it.amount.formatMNT(),
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold,
                )
                Text(
                    text = "Өнөөдөр, 16:24",
                    color = MaterialTheme.colorScheme.onPrimaryContainer,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    currentDailyBudget: Long,
    budgetGoalAmount: Long,
    budgetDurationInDays: Int,
    expenses: List<Expenses>?,
    expensesLoading: Boolean,
    onAddPress: () -> Unit,
) {
    Scaffold(
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddPress,
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text(stringResource(R.string.add_expense)) }
            )
        },
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            modifier = Modifier
                .background(MaterialTheme.colorScheme.surfaceVariant)
                .fillMaxSize(),
        ) {
            item {
                Surface(
                    shadowElevation = 5.dp,
                    modifier = Modifier
                        .background(MaterialTheme.colorScheme.surface)
                        .fillMaxWidth()
                ) {
                    Column(Modifier.padding(20.dp)) {
                        Text(
                            // TODO: Render greeting depending on the time of day
                            text = stringResource(R.string.greeting_afternoon),
                            style = MaterialTheme.typography.titleLarge,
                            color = MaterialTheme.colorScheme.onSurface,
                            fontWeight = FontWeight.Bold,
                        )
                        Text(
                            stringResource(
                                R.string.budget_goal,
                                budgetGoalAmount.formatMNT(),
                                budgetDurationInDays,
                            ),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Spacer(Modifier.height(80.dp))
                        Text(
                            text = stringResource(R.string.today_budget),
                            style = MaterialTheme.typography.titleSmall,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                        )
                        Text(
                            text = currentDailyBudget.formatMNT(),
                            style = MaterialTheme.typography.displayLarge,
                            color = MaterialTheme.colorScheme.onSurfaceVariant,
                            fontWeight = FontWeight.Bold,
                        )
                    }
                }
                Column(Modifier.padding(vertical = 15.dp, horizontal = 20.dp)) {
                    Text(
                        text = stringResource(R.string.expenses),
                        color = MaterialTheme.colorScheme.onSurfaceVariant,
                        style = MaterialTheme.typography.titleSmall,
                    )
                }
            }
            expenseList(loading = expensesLoading, expenses = expenses)
            item {
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}


@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreview() {
    val previewExpenses = remember {
        List(100) {
            Expenses(
                id = it.toLong(),
                amount = 10_000,
                date = 1681236069,
            )
        }
    }
    HetevchTheme {
        HomeScreen(
            currentDailyBudget = 2_500,
            budgetGoalAmount = 5_000,
            budgetDurationInDays = 3,
            expenses = previewExpenses,
            expensesLoading = false,
            onAddPress = {},
        )
    }
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenLoadingPreview() {
    HetevchTheme {
        HomeScreen(
            currentDailyBudget = 2_500,
            budgetGoalAmount = 5_000,
            budgetDurationInDays = 3,
            expenses = null,
            expensesLoading = true,
            onAddPress = {},
        )
    }
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenEmptyPreview() {
    HetevchTheme {
        HomeScreen(
            currentDailyBudget = 2_500,
            budgetGoalAmount = 5_000,
            budgetDurationInDays = 3,
            expenses = listOf(),
            expensesLoading = false,
            onAddPress = {},
        )
    }
}
