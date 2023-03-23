package co.icanteach.android.deeplinktester.settings.items

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import co.icanteach.android.deeplinktester.BuildConfig
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.settings.composables.SingleItem
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme

@Composable
fun AppVersion() {
    SingleItem(
        title = stringResource(id = R.string.settings_app_version_title),
        description = stringResource(
            id = R.string.settings_app_version_desc, BuildConfig.VERSION_NAME
        ),
        icon = painterResource(id = R.drawable.ic_version),
        clickable = false
    )
}

@ThemesPreview
@Composable
private fun AppVersion_Preview() {
    DeeplinkTesterTheme {
        Surface {
            AppVersion()
        }
    }
}