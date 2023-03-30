package co.icanteach.jumplink.home.composables

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import co.icanteach.android.apps.jumplink.R
import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.FakeDeepLinkItemFactory
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenStateWithDeepLinkHistory(
    enteredContent: String,
    historyDeepLinkItems: List<DeepLinkItem>,
    onEnteredContent: (String) -> Unit,
    onTestDeeplinkClicked: () -> Unit,
    onTestDeeplinkFromHistoryClicked: (DeepLinkItem) -> Unit,
    onClearDeeplinkClicked: () -> Unit,
    onSettingsItemClicked: () -> Unit,
    onPasteContent: () -> Unit,
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.app_name),
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
                ),
                actions = {
                    IconButton(
                        onClick = {
                            onSettingsItemClicked.invoke()
                        }) {
                        Icon(
                            imageVector = Icons.Filled.Settings,
                            contentDescription = stringResource(id = R.string.menu_item_settings)
                        )
                    }
                }
            )
        },
    ) { contentPaddingValue ->
        LazyColumn(
            contentPadding = contentPaddingValue
        ) {

            item {
                HomeScreenHeaderContent(
                    enteredContent = enteredContent,
                    onEnteredContent = { enteredContent ->
                        onEnteredContent.invoke(enteredContent)
                    },
                    onTestDeeplinkClicked = onTestDeeplinkClicked::invoke,
                    onClearDeeplinkClicked = onClearDeeplinkClicked::invoke,
                    onPasteContent = onPasteContent::invoke
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
    JumpLinkTheme {
        HomeScreenStateWithDeepLinkHistory(
            historyDeepLinkItems = FakeDeepLinkItemFactory.createDeepLinkItems(),
            enteredContent = "Deeplink",
            onEnteredContent = {},
            onTestDeeplinkClicked = {},
            onClearDeeplinkClicked = {},
            onTestDeeplinkFromHistoryClicked = {},
            onSettingsItemClicked = {},
            onPasteContent = {},
        )
    }
}