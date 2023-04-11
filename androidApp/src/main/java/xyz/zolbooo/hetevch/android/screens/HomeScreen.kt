package xyz.zolbooo.hetevch.android.screens

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.repository.Expenses

@Composable
fun HomeScreen(expenses: List<Expenses>) {
    Surface {
        val scrollState = rememberScrollState()
        Column(Modifier.verticalScroll(scrollState)) {
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
            Column(
                verticalArrangement = Arrangement.spacedBy(5.dp),
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surfaceVariant)
                    .padding(horizontal = 20.dp)
                    .fillMaxSize(),
            ) {
                Spacer(Modifier.height(15.dp))
                Text(
                    text = stringResource(R.string.expenses),
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.titleSmall,
                )
                Spacer(Modifier.height(5.dp))
                expenses.forEach {
                    key(it.id) {
                        ElevatedCard(Modifier.fillMaxWidth()) {
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
                }
                Spacer(Modifier.height(20.dp))
            }
        }
    }
}

@Preview(name = "Light mode")
@Composable
fun HomeScreenPreview() {
    HetevchTheme {
        Surface {
            HomeScreen(
                expenses = mutableListOf(
                    Expenses(
                        id = 1,
                        amount = 10_000,
                        date = 1681236069,
                    ), Expenses(
                        id = 2,
                        amount = 5_000,
                        date = 1681036069,
                    )
                ),
            )
        }
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun HomeScreenPreviewDarkMode() {
    HetevchTheme {
        Surface {
            HomeScreen(
                expenses = mutableListOf(
                    Expenses(
                        id = 1,
                        amount = 10_000,
                        date = 1681236069,
                    ),
                ),
            )
        }
    }
}
