package co.icanteach.android.deeplinktester.home.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.R
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun DeeplinkTextField(
    content: String,
    onEnteredContent: (String) -> Unit,
    onPasteContent: () -> Unit,
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        value = content,
        onValueChange = { enteredContent ->
            onEnteredContent.invoke(enteredContent)
        },
        label = {
            Text(stringResource(id = R.string.edittext_label_placeholder))
        },
        trailingIcon = {
            IconButton(
                onClick = {
                    onPasteContent.invoke()
                }) {
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_paste),
                    contentDescription = stringResource(id = R.string.paste_deeplink)
                )
            }
        }
    )
}

@ThemesPreview
@Composable
fun DeeplinkTextField_Preview() {
    JumpLinkTheme {
        DeeplinkTextField(
            content = "DeepLink Content",
            onEnteredContent = {},
            onPasteContent = {},
        )
    }
}