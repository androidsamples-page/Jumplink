package co.icanteach.android.deeplinktester.home.composables

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.ui.ThemesPreview
import co.icanteach.android.deeplinktester.ui.theme.DeeplinkTesterTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreenStateWithNoDeepLinkHistory(
    enteredContent: String,
    onEnteredContent: (String) -> Unit,
    onTestDeeplinkClicked: () -> Unit,
    onClearDeeplinkClicked: () -> Unit,
) {
    Scaffold(topBar = {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            }, colors = TopAppBarDefaults.smallTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                titleContentColor = MaterialTheme.colorScheme.onSurfaceVariant
            )
        )
    }) { contentPaddingValue ->
        LazyColumn(
            contentPadding = contentPaddingValue
        ) {

            item {
                HomeScreenHeaderContent(
                    enteredContent = enteredContent,
                    onEnteredContent = { enteredContent ->
                        onEnteredContent.invoke(enteredContent)
                    },
                    onTestDeeplinkClicked = {
                        onTestDeeplinkClicked.invoke()
                    },
                    onClearDeeplinkClicked = {
                        onClearDeeplinkClicked.invoke()
                    }
                )

                Spacer(modifier = Modifier.height(64.dp))
            }

            item {
                EmptyDeepLinkHistory()
            }
        }
    }
}


@Composable
@ThemesPreview
fun HomeScreenStateWithNoDeepLinkHistory_Preview() {
    DeeplinkTesterTheme {
        HomeScreenStateWithNoDeepLinkHistory(
            enteredContent = "Deeplink",
            onEnteredContent = {},
            onClearDeeplinkClicked = {},
            onTestDeeplinkClicked = {})
    }
}