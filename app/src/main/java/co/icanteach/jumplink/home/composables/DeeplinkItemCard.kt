package co.icanteach.jumplink.home.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.FakeDeepLinkItemFactory
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun DeeplinkItemCard(
    deepLinkItem: DeepLinkItem,
    onTestDeeplinkClicked: (DeepLinkItem) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        content = {
            Text(
                text = AnnotatedString(deepLinkItem.deeplink),
                modifier = Modifier
                    .padding(16.dp)
                    .clickable {
                        onTestDeeplinkClicked.invoke(deepLinkItem)
                    },
                textDecoration = TextDecoration.combine(
                    listOf(
                        TextDecoration.Underline
                    )
                ),
                style = MaterialTheme.typography.labelMedium
            )
        }
    )
}

@ThemesPreview
@Composable
fun DeeplinkItemCard_Preview() {
    JumpLinkTheme {
        DeeplinkItemCard(FakeDeepLinkItemFactory.createDeepLinkItem()) {}
    }
}