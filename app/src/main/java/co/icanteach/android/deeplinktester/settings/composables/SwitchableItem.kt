package co.icanteach.android.deeplinktester.settings.composables

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.ui.core.HorizontalSpacer
import co.icanteach.android.deeplinktester.ui.core.VerticalSpacer

@Composable
internal fun SwitchableItem(
    title: String,
    description: String,
    icon: Painter,
    isChecked: Boolean,
    onItemChanged: (Boolean) -> Unit = {},
) {

    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = icon,
            contentDescription = title,
            tint = MaterialTheme.colorScheme.onBackground
        )

        HorizontalSpacer(value = 16.dp)

        Column(
            modifier = Modifier.weight(1f)
        ) {
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

        Switch(checked = isChecked, onCheckedChange = onItemChanged)
    }
}