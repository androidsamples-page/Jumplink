package co.icanteach.android.deeplinktester.settings.items

import androidx.compose.foundation.layout.Column
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
fun ShareTheApp() {

    Column {
        SingleItem(
            title = stringResource(id = R.string.settings_share_this_application_title),
            description = stringResource(id = R.string.settings_share_this_application_desc),
            icon = painterResource(id = R.drawable.ic_share)
        ) {
            // TODO implement firebase app sharing
        }
        VerticalSpacer(value = 32.dp)
    }
}

@ThemesPreview
@Composable
fun ShareTheApp_Preview() {
    JumpLinkTheme {
        Surface {
            ShareTheApp()
        }
    }
}