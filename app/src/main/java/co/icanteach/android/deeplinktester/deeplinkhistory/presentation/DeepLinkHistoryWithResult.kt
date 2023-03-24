package co.icanteach.android.deeplinktester.deeplinkhistory.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.DeepLinkItem

@Composable
fun DeepLinkHistoryWithResult(
    historyItems: List<DeepLinkItem> = emptyList(),
    contentPaddingValue: PaddingValues,
    onTestDeeplinkClicked: (DeepLinkItem) -> Unit,
    onDeleteDeeplinkItemClicked: (DeepLinkItem) -> Unit,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        contentPadding = contentPaddingValue
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