package co.icanteach.jumplink.settings.items

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.icanteach.android.apps.jumplink.R
import co.icanteach.jumplink.settings.composables.SingleItem
import core.libraries.design.composables.VerticalSpacer
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun AppSourceCode(
    onAppSourceCodeNavigationClicked: () -> Unit,
) {

    Column {
        SingleItem(
            title = stringResource(id = R.string.settings_app_source_title),
            description = stringResource(id = R.string.settings_app_source_desc),
            icon = painterResource(id = R.drawable.ic_github)
        ) {
            onAppSourceCodeNavigationClicked.invoke()
        }
        VerticalSpacer(value = 32.dp)
    }
}

@ThemesPreview
@Composable
fun AppSourceCode_Preview() {
    JumpLinkTheme {
        Surface {
            AppSourceCode(
                onAppSourceCodeNavigationClicked = {}
            )
        }
    }
}
