package co.icanteach.android.deeplinktester.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.deeplinkhistory.presentation.DeepLinkHistoryPageViewState
import co.icanteach.android.deeplinktester.settings.items.AppVersion
import co.icanteach.android.deeplinktester.settings.items.NavigateHistory
import co.icanteach.android.deeplinktester.settings.items.RateTheApp
import co.icanteach.android.deeplinktester.settings.items.ShareTheApp

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateHistoryScreenClicked: () -> Unit,
) {

    val scrollableState = rememberScrollState()
    SettingsScreen(
        scrollableState,
        onNavigateHistoryScreenClicked = {
            onNavigateHistoryScreenClicked.invoke()
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    scrollableState: ScrollState,
    onNavigateHistoryScreenClicked: () -> Unit,
) {

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        stringResource(id = R.string.settings_page_title)
                    )
                },
            )
        },
    ) { contentPaddingValue ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollableState)
                .padding(contentPaddingValue)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            NavigateHistory(onNavigateHistoryScreenClicked = {
                onNavigateHistoryScreenClicked.invoke()
            })
            ShareTheApp()
            RateTheApp()
            AppVersion()
        }
    }

}