package co.icanteach.android.deeplinktester.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme

@Composable
fun DeepLinkActions(
    onTestDeeplinkClicked: () -> Unit,
    onClearDeeplinkClicked: () -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        FilledTonalButton(onClick = {
            onTestDeeplinkClicked.invoke()
        }) {
            Text(text = stringResource(id = R.string.button_test_deeplink))
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
    DeeplinkTesterTheme {
        DeepLinkActions(
            onTestDeeplinkClicked = {},
            onClearDeeplinkClicked = {}
        )
    }
}