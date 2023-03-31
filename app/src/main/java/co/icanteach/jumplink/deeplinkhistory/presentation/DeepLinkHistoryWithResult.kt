package co.icanteach.jumplink.deeplinkhistory.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.FakeDeepLinkItemFactory
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun DeepLinkHistoryWithResult(
    historyItems: List<DeepLinkItem> = emptyList(),
    contentPaddingValue: PaddingValues,
    onTestDeeplinkClicked: (DeepLinkItem) -> Unit,
    onDeleteDeeplinkItemClicked: (DeepLinkItem) -> Unit,
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = contentPaddingValue.calculateTopPadding(),
            ),
        contentPadding = PaddingValues(
            horizontal = 16.dp,
            vertical = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {

        items(historyItems) { deepLinkItem ->
            Row(modifier = Modifier.fillMaxWidth()) {
                DeeplinkHistoryItemCard(
                    deepLinkItem,
                    onDeleteDeeplinkItemClicked = onDeleteDeeplinkItemClicked::invoke,
                    onTestDeeplinkClicked = onTestDeeplinkClicked::invoke
                )
            }
        }
    }
}

@ThemesPreview
@Composable
fun DeepLinkHistoryWithResult_Preview() {
    JumpLinkTheme {
        DeepLinkHistoryWithResult(
            FakeDeepLinkItemFactory.createDeepLinkItems(),
            contentPaddingValue = PaddingValues(16.dp),
            onTestDeeplinkClicked = {},
            onDeleteDeeplinkItemClicked = {},
        )
    }
}