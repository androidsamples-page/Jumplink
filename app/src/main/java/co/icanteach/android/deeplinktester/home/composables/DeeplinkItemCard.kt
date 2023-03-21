package co.icanteach.android.deeplinktester.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.FakeDeepLinkItemFactory
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme

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

@ThemesPreview
@Composable
fun DeeplinkItemCard_Preview() {
    DeeplinkTesterTheme {
        DeeplinkItemCard(FakeDeepLinkItemFactory.createDeepLinkItem())
    }
}