package co.icanteach.android.deeplinktester.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme

@Composable
fun DeeplinkTextField(
    content: String,
    onEnteredContent: (String) -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        value = content,
        onValueChange = { enteredContent ->
            onEnteredContent.invoke(enteredContent)
        },
        label = { Text(stringResource(id = R.string.edittext_label_placeholder)) }
    )
}


@ThemesPreview
@Composable
fun DeeplinkTextField_Preview() {
    DeeplinkTesterTheme {
        DeeplinkTextField(
            content = "DeepLink Content",
            onEnteredContent = {}
        )
    }
}