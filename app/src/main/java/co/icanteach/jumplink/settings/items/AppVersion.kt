package co.icanteach.jumplink.settings.items

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import co.icanteach.android.apps.jumplink.BuildConfig
import co.icanteach.android.apps.jumplink.R
import co.icanteach.jumplink.settings.composables.SingleItem
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

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
    JumpLinkTheme {
        Surface {
            AppVersion()
        }
    }
}