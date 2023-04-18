package xyz.zolbooo.hetevch.android.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.utils.formatMNT
import xyz.zolbooo.hetevch.repository.Expenses
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@Composable
fun ExpenseCard(expense: Expenses, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier) {
        Column(Modifier.padding(horizontal = 20.dp, vertical = 15.dp)) {
            Text(
                text = expense.amount.formatMNT(),
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
            )
            val expenseDate = remember(expense.date) {
                Instant.ofEpochSecond(expense.date)
                    .atZone(ZoneId.systemDefault())
                    .toLocalDateTime()
            }
            val today = remember { LocalDate.now() }
            val dateString = when {
                today.equals(expenseDate.toLocalDate()) -> stringResource(R.string.today)
                today.minusDays(1)
                    .equals(expenseDate.toLocalDate()) -> stringResource(R.string.yesterday)

                else -> expenseDate.format(DateTimeFormatter.ofPattern(stringResource(R.string.date_pattern)))
            }
            val timeString =
                "${expenseDate.hour}:${expenseDate.minute.toString().padStart(2, '0')}"
            Text(
                text = "$dateString, $timeString",
                color = MaterialTheme.colorScheme.onPrimaryContainer,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}
