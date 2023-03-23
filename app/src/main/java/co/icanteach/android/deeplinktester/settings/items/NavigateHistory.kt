package co.icanteach.android.deeplinktester.settings.items

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.settings.composables.SingleItem
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.core.VerticalSpacer
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme

@Composable
fun NavigateHistory() {
    SingleItem(
        title = stringResource(id = R.string.settings_navigate_history_title),
        description = stringResource(id = R.string.settings_navigate_history_desc),
        icon = painterResource(id = R.drawable.ic_history)
    ) {
        // TODO inappreview.
    }

    VerticalSpacer(value = 32.dp)
}

@ThemesPreview
@Composable
fun NavigateHistory_Preview() {
    DeeplinkTesterTheme {
        Surface {
            NavigateHistory()
        }
    }
}