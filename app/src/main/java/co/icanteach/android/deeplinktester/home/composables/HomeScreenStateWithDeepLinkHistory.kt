package co.icanteach.android.deeplinktester.home.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.FakeDeepLinkItemFactory
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenStateWithDeepLinkHistory(
    enteredContent: String,
    historyDeepLinkItems: List<DeepLinkItem>,
    onEnteredContent: (String) -> Unit,
    onTestDeeplinkClicked: () -> Unit,
    onTestDeeplinkFromHistoryClicked: (DeepLinkItem) -> Unit,
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
    }) { contentPaddingValue ->
        LazyColumn(
            contentPadding = contentPaddingValue
        ) {

            item {
                HomeScreenHeaderContent(
                    enteredContent = enteredContent,
                    onEnteredContent = { enteredContent ->
                        onEnteredContent.invoke(enteredContent)
                    },
                    onTestDeeplinkClicked = {
                        onTestDeeplinkClicked.invoke()
                    },
                    onClearDeeplinkClicked = {
                        onClearDeeplinkClicked.invoke()
                    }
                )
            }

            item {
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

            items(historyDeepLinkItems) { deepLinkItem ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    DeeplinkItemCard(deepLinkItem) {
                        onTestDeeplinkFromHistoryClicked.invoke(it)
                    }
                }
            }
        }
    }
}

@Composable
@ThemesPreview
fun HomeScreenStateWithDeepLinkHistory_Preview() {
    DeeplinkTesterTheme {
        HomeScreenStateWithDeepLinkHistory(
            historyDeepLinkItems = FakeDeepLinkItemFactory.createDeepLinkItems(),
            enteredContent = "Deeplink",
            onEnteredContent = {},
            onTestDeeplinkClicked = {},
            onTestDeeplinkFromHistoryClicked = {}
        ) {}
    }
}