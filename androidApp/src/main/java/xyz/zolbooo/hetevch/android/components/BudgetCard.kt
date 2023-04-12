package xyz.zolbooo.hetevch.android.components

import android.content.res.Configuration
import android.icu.text.NumberFormat
import android.icu.util.Currency
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.ui.HetevchTheme
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.utils.formatMNT

@Composable
fun BudgetCard(amount: Long, modifier: Modifier = Modifier) {
    ElevatedCard(modifier = modifier) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(horizontal = 10.dp, vertical = 10.dp)
                .fillMaxWidth(),
        ) {
            Column(
                Modifier
                    .align(Alignment.Bottom)
                    .padding(start = 10.dp)
            ) {
                // TODO: We can possibly use Inter font for this label
                Text(
                    text = stringResource(R.string.my_goal),
                    style = MaterialTheme.typography.bodySmall,
                )
                Text(text = amount.formatMNT(), style = MaterialTheme.typography.titleLarge)
            }
            Spacer(Modifier.width(10.dp))
            Image(
                painter = painterResource(R.drawable.budget),
                contentDescription = "A picture of a person planning a budget",
                modifier = Modifier.align(Alignment.CenterVertically),
            )
        }
    }
}

@Preview(name = "Light mode")
@Composable
fun BudgetCardPreview() {
    HetevchTheme {
        BudgetCard(
            amount = 380_000L,
            modifier = Modifier
                .width(350.dp)
                .height(150.dp),
        )
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun BudgetCardPreviewDarkMode() {
    HetevchTheme {
        BudgetCard(
            amount = 380_000L,
            modifier = Modifier
                .width(350.dp)
                .height(150.dp),
        )
    }
}
