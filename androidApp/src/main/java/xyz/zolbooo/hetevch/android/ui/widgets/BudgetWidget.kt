package xyz.zolbooo.hetevch.android.ui.widgets

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.ui.components.BudgetCard
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.android.utils.AmountVisualTransformation
import xyz.zolbooo.hetevch.android.utils.formatMNT
import java.time.Clock
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

@Composable
private fun formatDateFromNow(formatter: DateTimeFormatter, days: Int): String {
    val clock = Clock.systemDefaultZone()
    val date = clock.instant()
        .plus(days.toLong(), ChronoUnit.DAYS)
        .atZone(clock.zone)
        .toLocalDate()
    return "${formatter.format(date)} | ${stringResource(R.string.duration, days)}"
}

private fun calculateDailyBudget(amount: String, days: Int?): Long? {
    val amountLong = amount.toLongOrNull() ?: return null
    return amountLong / (days ?: return null)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BudgetWidget(
    onSave: (Long, Int) -> Unit,
    modifier: Modifier = Modifier,
    onIncorrectInput: () -> Unit = {},
) {
    var amount by remember { mutableStateOf("") }
    var duration by remember { mutableStateOf<Int?>(null) }
    Column(modifier.padding(20.dp)) {
        BudgetCard(amount = 0)
        Spacer(Modifier.height(20.dp))
        Column(verticalArrangement = Arrangement.spacedBy(10.dp)) {
            OutlinedTextField(
                label = { Text(text = stringResource(R.string.budget_amount)) },
                visualTransformation = AmountVisualTransformation,
                value = amount,
                onValueChange = { amount = it },
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next,
                ),
                modifier = Modifier.fillMaxWidth(),
            )

            var expanded by remember { mutableStateOf(false) }
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
            ) {
                val formatter = remember { DateTimeFormatter.ofPattern("MMM dd") }
                OutlinedTextField(
                    label = { Text(text = stringResource(R.string.budget_duration)) },
                    value = duration?.let { formatDateFromNow(formatter, it) } ?: "",
                    onValueChange = {},
                    readOnly = true,
                    trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                    colors = ExposedDropdownMenuDefaults.textFieldColors(),
                    modifier = Modifier
                        .menuAnchor()
                        .fillMaxWidth(),
                )
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    for (days in 1..45) {
                        DropdownMenuItem(
                            text = { Text(text = formatDateFromNow(formatter, days)) },
                            onClick = {
                                duration = days
                                expanded = false
                            },
                            contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                        )
                    }
                }
            }

            OutlinedTextField(
                label = { Text(stringResource(R.string.budget_daily_amount)) },
                value = calculateDailyBudget(amount, duration)?.formatMNT() ?: "",
                onValueChange = {},
                enabled = false,
                modifier = Modifier.fillMaxWidth(),
            )
        }
        val buttonIsEnabled = remember(amount, duration) {
            (amount.toLongOrNull() != null) && (duration != null)
        }
        Button(
            enabled = buttonIsEnabled,
            onClick = {
                val amountAsLong = amount.toLongOrNull()
                val durationValue = duration
                if (amountAsLong != null && durationValue != null) {
                    onSave(amountAsLong, durationValue)
                } else {
                    onIncorrectInput()
                }
            },
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxWidth(),
        ) {
            Text(stringResource(R.string.budget_save))
        }
    }
}

@Preview(name = "Light mode")
@Composable
fun BudgetWidgetPreview() {
    HetevchTheme {
        Surface {
            BudgetWidget(onSave = { _, _ -> })
        }
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BudgetWidgetPreviewDarkMode() {
    HetevchTheme {
        Surface {
            BudgetWidget(onSave = { _, _ -> })
        }
    }
}
