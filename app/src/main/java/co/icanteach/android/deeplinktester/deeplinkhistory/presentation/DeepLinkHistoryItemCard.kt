package co.icanteach.android.deeplinktester.deeplinkhistory.presentation

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.FakeDeepLinkItemFactory
import co.icanteach.android.deeplinktester.R
import core.libraries.design.composables.HorizontalSpacer
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun DeeplinkHistoryItemCard(
    deepLinkItem: DeepLinkItem,
    onTestDeeplinkClicked: (DeepLinkItem) -> Unit,
    onDeleteDeeplinkItemClicked: (DeepLinkItem) -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        content = {
            Row(
                modifier = Modifier
                    .padding(all = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    modifier = Modifier.size(32.dp),
                    onClick = {
                        onDeleteDeeplinkItemClicked.invoke(deepLinkItem)
                    }) {
                    Icon(
                        imageVector = Icons.Filled.Clear,
                        contentDescription = stringResource(id = R.string.history_page_delete_all)
                    )
                }
                HorizontalSpacer(value = 16.dp)
                Text(
                    text = AnnotatedString(deepLinkItem.deeplink),
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onTestDeeplinkClicked.invoke(deepLinkItem)
                        },
                    textDecoration = TextDecoration.combine(
                        listOf(
                            TextDecoration.Underline
                        )
                    ),
                    style = MaterialTheme.typography.labelSmall
                )
            }
        }
    )
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