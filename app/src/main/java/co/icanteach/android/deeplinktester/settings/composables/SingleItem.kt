package co.icanteach.android.deeplinktester.settings.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.ui.core.HorizontalSpacer

@Composable
internal fun SingleItem(
    title: String,
    description: String,
    icon: Painter,
    clickable: Boolean = true,
    onItemClicked: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(
                enabled = clickable,
                onClick = onItemClicked
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon, contentDescription = title
        )

        HorizontalSpacer(value = 16.dp)
        SettingsContent(title = title, description = description)
    }
}