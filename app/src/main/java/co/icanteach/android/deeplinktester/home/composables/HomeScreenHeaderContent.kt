package co.icanteach.android.deeplinktester.home.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun HomeScreenHeaderContent(
    enteredContent: String,
    onEnteredContent: (String) -> Unit,
    onTestDeeplinkClicked: () -> Unit,
    onClearDeeplinkClicked: () -> Unit,
    onPasteContent: () -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        DeeplinkTextField(
            content = enteredContent,
            onEnteredContent = { enteredContent ->
                onEnteredContent.invoke(enteredContent)
            },
            onPasteContent = {
                onPasteContent.invoke()
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        DeepLinkActions(
            onTestDeeplinkClicked = {
                onTestDeeplinkClicked.invoke()
            },
            onClearDeeplinkClicked = {
                onClearDeeplinkClicked.invoke()
            }
        )
        Spacer(modifier = Modifier.height(32.dp))
    }
}


@ThemesPreview
@Composable
fun HomeScreenHeaderContent_Preview() {
    JumpLinkTheme {
        HomeScreenHeaderContent(
            enteredContent = "DeepLink Content",
            onEnteredContent = {},
            onClearDeeplinkClicked = {},
            onTestDeeplinkClicked = {},
            onPasteContent = {},
        )
    }
}