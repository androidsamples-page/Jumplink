package co.icanteach.jumplink.deeplinkhistory.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Share
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.FakeDeepLinkItemFactory
import core.libraries.design.composables.VerticalSpacer
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun DeeplinkHistoryItemCard(
    deepLinkItem: DeepLinkItem,
    onTestDeeplinkClicked: (DeepLinkItem) -> Unit,
    onDeleteDeeplinkItemClicked: (DeepLinkItem) -> Unit,
) {
    Card {
        Column(
            Modifier
                .fillMaxWidth()
                .padding(all = 16.dp)
        ) {
            Text(
                text = deepLinkItem.deeplink,
                style = MaterialTheme.typography.bodyLarge,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.clickable {
                    onTestDeeplinkClicked.invoke(deepLinkItem)
                }
            )

            VerticalSpacer(value = 8.dp)

            Text(
                text = deepLinkItem.createdDate,
                style = MaterialTheme.typography.bodySmall
            )

            VerticalSpacer(value = 16.dp)

            Box(
                Modifier
                    .fillMaxWidth()
            ) {

                Row(
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    IconButton(
                        onClick = {
                            onDeleteDeeplinkItemClicked.invoke(deepLinkItem)
                        }) {
                        Icon(Icons.Default.Delete, contentDescription = null)
                    }
                }
            }
        }
    }
}


@ThemesPreview
@Composable
fun DeeplinkHistoryItemCard_Preview() {
    JumpLinkTheme {
        DeeplinkHistoryItemCard(
            FakeDeepLinkItemFactory.createDeepLinkItem(),
            onTestDeeplinkClicked = {},
            onDeleteDeeplinkItemClicked = {}
        )
    }
}