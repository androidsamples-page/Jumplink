package co.icanteach.android.deeplinktester.settings.items

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.settings.composables.SingleItem
import core.libraries.design.composables.VerticalSpacer
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun RateTheApp() {
    SingleItem(
        title = stringResource(id = R.string.settings_rate_us_title),
        description = stringResource(id = R.string.settings_rate_us_desc),
        icon = painterResource(id = R.drawable.ic_rate)
    ) {
        // TODO inappreview.
    }

    VerticalSpacer(value = 32.dp)
}

@ThemesPreview
@Composable
fun RateTheApp_Preview() {
    JumpLinkTheme {
        Surface {
            RateTheApp()
        }
    }
}
