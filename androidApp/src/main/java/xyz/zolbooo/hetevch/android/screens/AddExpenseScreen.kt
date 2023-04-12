package xyz.zolbooo.hetevch.android.screens

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.components.KeyPad
import xyz.zolbooo.hetevch.android.ui.HetevchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddExpenseScreen(balance: Long, onBackPress: () -> Unit) {
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
        Column(Modifier.padding(paddingValues)) {
            Spacer(Modifier.height(20.dp))
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .weight(1f),
            ) {
                Text(
                    stringResource(R.string.remaining_balance, balance.toString()),
                    style = MaterialTheme.typography.titleSmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    fontWeight = FontWeight.Light,
                )
                // TODO: Render width-filling text component
                Text(
                    "MNT 0",
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
                    KeyPad(onPress = {})
                    Spacer(Modifier.height(10.dp))
                    Button(
                        onClick = { /*TODO*/ },
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
@Composable
fun AddExpenseScreenPreview() {
    HetevchTheme {
        AddExpenseScreen(balance = 12_000L, onBackPress = {})
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun AddExpenseScreenPreviewDarkMode() {
    HetevchTheme {
        AddExpenseScreen(balance = 20_000L, onBackPress = {})
    }
}
