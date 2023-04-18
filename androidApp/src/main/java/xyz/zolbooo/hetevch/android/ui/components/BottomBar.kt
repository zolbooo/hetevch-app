package xyz.zolbooo.hetevch.android.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalanceWallet
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import xyz.zolbooo.hetevch.android.R

@Composable
fun BottomBar(onHomePress: () -> Unit, onBudgetPress: () -> Unit, onAddExpensePress: () -> Unit) {
    BottomAppBar(
        actions = {
            IconButton(onClick = onHomePress) {
                Icon(Icons.Filled.Home, contentDescription = null)
            }
            IconButton(onClick = onBudgetPress) {
                Icon(Icons.Filled.AccountBalanceWallet, contentDescription = null)
            }
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = onAddExpensePress,
                icon = { Icon(Icons.Filled.Add, contentDescription = null) },
                text = { Text(stringResource(R.string.add_expense)) },
                containerColor = BottomAppBarDefaults.bottomAppBarFabColor,
                elevation = FloatingActionButtonDefaults.bottomAppBarFabElevation(),
            )
        },
    )
}
