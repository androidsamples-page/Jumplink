package co.icanteach.android.deeplinktester.home

import android.content.Context
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.FakeDeepLinkItemFactory
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
) {

    val currentContext = LocalContext.current
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateDeepLinkContent -> {
                    onNavigateDeepLinkContent(
                        context = currentContext,
                        deepLinkContent = event.deepLinkContent
                    )
                }
                is UiEvent.ShowError -> {
                    // TODO add snackbar here
                }
            }
        }
    }

    HomeScreenContent(
        uiState = uiState,
        onEnteredContent = { enteredContent ->
            viewModel.onAction(HomeScreenActions.EnteredContent(enteredContent))
        },
        onTestDeeplinkClicked = {
            viewModel.onAction(HomeScreenActions.TestEnteredContent)
        },
        onClearDeeplinkClicked = {
            viewModel.onAction(HomeScreenActions.ClearEnteredContent)
        }
    )
}

fun onNavigateDeepLinkContent(
    deepLinkContent: String,
    context: Context
) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(deepLinkContent)
    startActivity(context, intent, null)
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HomeScreenContent(
    uiState: HomeScreenPageViewState,
    onEnteredContent: (String) -> Unit,
    onTestDeeplinkClicked: () -> Unit,
    onClearDeeplinkClicked: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
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
                DeeplinkTextField(
                    content = uiState.enteredContent,
                    onEnteredContent = { enteredContent ->
                        onEnteredContent.invoke(enteredContent)
                    }
                )
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
                DeepLinkActions(
                    onTestDeeplinkClicked = {
                        onTestDeeplinkClicked.invoke()
                    },
                    onClearDeeplinkClicked = {
                        onClearDeeplinkClicked.invoke()
                    }
                )
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
fun DeeplinkTextField(
    content: String,
    onEnteredContent: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        value = content,
        onValueChange = { enteredContent ->
            onEnteredContent.invoke(enteredContent)
        },
        label = { Text(stringResource(id = R.string.edittext_label_placeholder)) }
    )
}

@Composable
fun DeepLinkActions(
    onTestDeeplinkClicked: () -> Unit,
    onClearDeeplinkClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilledTonalButton(onClick = {
            onTestDeeplinkClicked.invoke()
        }) {
            Text(text = stringResource(id = R.string.button_test_deeplink))
        }
        FilledTonalButton(onClick = {
            onClearDeeplinkClicked.invoke()
        }) {
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