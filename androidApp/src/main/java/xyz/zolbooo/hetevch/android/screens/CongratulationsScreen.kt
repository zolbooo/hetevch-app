package xyz.zolbooo.hetevch.android.screens

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.ui.HetevchTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CongratulationsScreen() {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(stringResource(R.string.congratulations)) },
            )
        },
    ) { paddingValues ->
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxWidth(),
        ) {
            Spacer(Modifier.height(25.dp))
            Image(
                painter = painterResource(R.drawable.saving), contentDescription = null
            )
            Spacer(Modifier.height(25.dp))
            // TODO: Render different strings depending on open date
            Text(
                text = stringResource(R.string.your_saving_yesterday),
                style = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.Light,
            )
            Text(
                text = "₮2500",
                style = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
            )
            Spacer(Modifier.height(40.dp))
            Column(verticalArrangement = Arrangement.Bottom, modifier = Modifier.weight(1f)) {
                Card(Modifier.padding(20.dp)) {
                    Column(Modifier.padding(horizontal = 20.dp, vertical = 24.dp)) {
                        Text(
                            text = stringResource(R.string.what_would_you_like_to_do),
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.bodyLarge,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 20.dp),
                        )
                        Spacer(Modifier.height(24.dp))
                        OutlinedButton(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth()) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .fillMaxWidth(),
                            ) {
                                Text(stringResource(R.string.add_to_budget))
                                Text(
                                    text = stringResource(
                                        R.string.your_budget_will_be, "2700₮ / 2 өдөр"
                                    ),
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        OutlinedButton(onClick = { /*TODO*/ }) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .fillMaxWidth(),
                            ) {
                                Text(stringResource(R.string.add_to_today_budget))
                                Text(
                                    text = stringResource(
                                        R.string.your_budget_for_today_will_be, "2700₮"
                                    ),
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                        Spacer(Modifier.height(10.dp))
                        OutlinedButton(onClick = { /*TODO*/ }) {
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.spacedBy(5.dp),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp, vertical = 8.dp)
                                    .fillMaxWidth(),
                            ) {
                                Text(stringResource(R.string.save_money))
                                Text(
                                    text = stringResource(R.string.your_budget_will_not_change),
                                    style = MaterialTheme.typography.bodySmall,
                                    textAlign = TextAlign.Center,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview(name = "Light mode")
@Composable
fun CongratulationsScreenPreview() {
    HetevchTheme {
        CongratulationsScreen()
    }
}

@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun CongratulationsScreenPreviewDarkMode() {
    HetevchTheme {
        CongratulationsScreen()
    }
}