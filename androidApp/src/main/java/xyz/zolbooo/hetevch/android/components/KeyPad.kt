package xyz.zolbooo.hetevch.android.components

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Backspace
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import xyz.zolbooo.hetevch.android.R
import xyz.zolbooo.hetevch.android.ui.HetevchTheme

sealed class KeyPadButton {
    data class Digit(val value: Int) : KeyPadButton()
    object Delete : KeyPadButton()
    object TripleZero : KeyPadButton()
}

@Composable
fun KeyPad(onPress: (KeyPadButton) -> Unit) {
    Column(verticalArrangement = Arrangement.spacedBy(5.dp), modifier = Modifier.fillMaxWidth()) {
        repeat(3) { i ->
            Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                repeat(3) { j ->
                    Card(Modifier.weight(1f)) {
                        Box(
                            modifier = Modifier
                                .clickable { onPress(KeyPadButton.Digit(i * 3 + j + 1)) }
                                .padding(20.dp)
                                .padding(bottom = 20.dp)
                                .fillMaxWidth(),
                        ) {
                            Text((i * 3 + j + 1).toString())
                        }
                    }
                }
            }
        }
        Row(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            Card(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .clickable { onPress(KeyPadButton.TripleZero) }
                        .padding(20.dp)
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()) {
                    Text("000")
                }
            }
            Card(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .clickable { onPress(KeyPadButton.Digit(0)) }
                        .padding(20.dp)
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()) {
                    Text("0")
                }
            }
            Card(Modifier.weight(1f)) {
                Box(
                    Modifier
                        .clickable { onPress(KeyPadButton.Delete) }
                        .padding(20.dp)
                        .padding(bottom = 20.dp)
                        .fillMaxWidth()) {
                    Icon(
                        Icons.Filled.Backspace,
                        contentDescription = stringResource(R.string.backspace),
                    )
                }
            }
        }
    }
}

@Preview(name = "Light mode")
@Preview(name = "Dark mode", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun KeyPadPreview() {
    HetevchTheme {
        Box(Modifier.padding(20.dp)) {
            KeyPad(onPress = {})
        }
    }
}
