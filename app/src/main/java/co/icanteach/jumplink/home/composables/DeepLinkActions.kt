package co.icanteach.jumplink.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.icanteach.android.apps.jumplink.R
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun DeepLinkActions(
    onOpenAppClicked: () -> Unit,
    onClearDeeplinkClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilledTonalButton(onClick = {
            onOpenAppClicked.invoke()
        }) {
            Text(text = stringResource(id = R.string.button_open_app))
        }
        FilledTonalButton(onClick = {
            onClearDeeplinkClicked.invoke()
        }) {
            Text(text = stringResource(id = R.string.button_clear_deeplink))
        }
    }
}

@ThemesPreview
@Composable
fun DeepLinkActions_Preview() {
    JumpLinkTheme {
        DeepLinkActions(
            onOpenAppClicked = {},
            onClearDeeplinkClicked = {}
        )
    }
}