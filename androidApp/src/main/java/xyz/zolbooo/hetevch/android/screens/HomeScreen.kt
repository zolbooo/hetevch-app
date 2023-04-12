package xyz.zolbooo.hetevch.android.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.repository.Expenses

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(expenses: List<Expenses>, onAddPress: () -> Unit) {
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
                            // TODO: Render budget summary
                            "₮5000 / 2 өдөр",
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
                            // TODO: Render remaining budget for today
                            text = "₮2,500",
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
            items(items = expenses, key = { it.id }) {
                ElevatedCard(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 20.dp)
                        .padding(bottom = 5.dp)
                ) {
                    Column(Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
                        Text(
                            text = it.amount.toString(),
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
            item {
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

private val previewExpenses = List(100) {
    Expenses(
        id = it.toLong(),
        amount = 10_000,
        date = 1681236069,
    )
}

@Preview(name = "Light mode")
@Composable
fun HomeScreenPreview() {
    HetevchTheme {
        HomeScreen(
            expenses = previewExpenses,
            onAddPress = {},
        )
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDarkMode() {
    HetevchTheme {
        HomeScreen(
            expenses = previewExpenses,
            onAddPress = {},
        )
    }
}