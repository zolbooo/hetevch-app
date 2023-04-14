package xyz.zolbooo.hetevch.android.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.components.KeyPad
import xyz.zolbooo.hetevch.android.components.KeyPadButton
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.android.utils.formatMNT
import kotlin.math.max

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(balance: Long, onAddExpense: (Long) -> Unit, onBackPress: () -> Unit) {
    Scaffold(topBar = {
        TopAppBar(title = { Text(stringResource(R.string.add_expense)) }, navigationIcon = {
            IconButton(onClick = onBackPress) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = stringResource(R.string.back),
                )
            }
        })
    }) { paddingValues ->
        var amount by remember { mutableStateOf(0L) }
        Column(Modifier.padding(paddingValues)) {
            Spacer(Modifier.height(20.dp))
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f),
            ) {
                // TODO: We can render warning text when there will no be remaining balance after current expense
                Text(
                    stringResource(
                        R.string.remaining_balance,
                        max(balance - amount, 0).formatMNT()
                    ),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Light,
                )
                // TODO: Render width-filling text component
                Text(
                    amount.formatMNT(),
                    style = MaterialTheme.typography.headlineLarge,
                    fontWeight = FontWeight.Bold,
                )
            }
            Spacer(Modifier.height(20.dp))
            Surface(
                color = MaterialTheme.colorScheme.secondaryContainer,
                modifier = Modifier.fillMaxWidth(),
            ) {
                Column(Modifier.padding(20.dp)) {
                    KeyPad(onPress = { pressedButton ->
                        when (pressedButton) {
                            is KeyPadButton.Digit -> amount = amount * 10 + pressedButton.value
                            is KeyPadButton.Delete -> amount /= 10
                            is KeyPadButton.TripleZero -> amount *= 1000
                        }
                    })
                    Spacer(Modifier.height(10.dp))
                    Button(
                        onClick = {
                            if (amount > 0) {
                                onAddExpense(amount)
                            } else {
                                // TODO: Trigger a shake animation on amount text
                            }
                        },
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(R.string.save))
                    }
                    OutlinedButton(
                        onClick = onBackPress,
                        modifier = Modifier.fillMaxWidth(),
                    ) {
                        Text(stringResource(R.string.back))
                    }
                }
            }
        }
    }
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddExpenseScreenPreview() {
    HetevchTheme {
        AddExpenseScreen(balance = 12_000L, onAddExpense = {}, onBackPress = {})
    }
}
