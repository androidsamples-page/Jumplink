package co.icanteach.android.deeplinktester

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SmallTopAppBar
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    Scaffold(topBar = {
        SmallTopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }) { contentPadding ->
        LazyColumn(
            contentPadding = contentPadding
        ) {

            item {
                Spacer(modifier = Modifier.height(16.dp))
                DeeplinkTextField()
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                DeepLinkActions()
            }

            item {
                Spacer(modifier = Modifier.height(32.dp))
                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(id = R.string.text_title_deeplink_history)
                )
                Spacer(modifier = Modifier.height(16.dp))
                Divider(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    thickness = 0.2.dp
                )
                Spacer(modifier = Modifier.height(16.dp))
            }

            items(FakeDeepLinkItemFactory.createDeepLinkItems()) { deepLinkItem ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    DeeplinkItemCard(deepLinkItem)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeeplinkItemCard(
    deepLinkItem: DeepLinkItem
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        content = {
            Text(
                deepLinkItem.deeplink,
                modifier = Modifier.padding(16.dp),
                style = MaterialTheme.typography.labelMedium
            )
        }
    )
}

@Preview
@Composable
fun DeeplinkItemCard_Preview() {
    DeeplinkItemCard(deepLinkItem = FakeDeepLinkItemFactory.createDeepLinkItem())
}

@Composable
fun DeeplinkTextField() {
    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        value = text,
        onValueChange = { text = it },
        label = { Text(stringResource(id = R.string.edittext_label_placeholder)) }
    )
}

@Composable
fun DeepLinkActions() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.button_test_deeplink))
        }
        FilledTonalButton(onClick = { /*TODO*/ }) {
            Text(text = stringResource(id = R.string.button_clear_deeplink))
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun DeepLinkActions_Preview() {
    DeeplinkTesterTheme {
        HomeScreen()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun DeepLinkActions_DarkPreview() {
    DeeplinkTesterTheme {
        HomeScreen()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreen_Preview() {
    DeeplinkTesterTheme {
        HomeScreen()
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true,
    uiMode = UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreen_DarkPreview() {
    DeeplinkTesterTheme {
        HomeScreen()
    }
}