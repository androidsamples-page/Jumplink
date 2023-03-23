package co.icanteach.android.deeplinktester.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.core.VerticalSpacer
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme

@Composable
fun SettingsContent(
    title: String,
    description: String,
) {
    Column {
        Text(
            text = title,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.titleSmall
        )

        VerticalSpacer(value = 1.dp)

        Text(
            text = description,
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@ThemesPreview
@Composable
fun SettingsContent() {
    DeeplinkTesterTheme {
        Surface {
            SettingsContent(
                title = "Share this application!",
                description = "Invite your friend to #deeplinktester"
            )
        }
    }

}