package co.icanteach.android.deeplinktester.settings

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.home.composables.HomeTopAppBar
import co.icanteach.android.deeplinktester.settings.items.AppVersion
import co.icanteach.android.deeplinktester.settings.items.NavigateHistory
import co.icanteach.android.deeplinktester.settings.items.RateTheApp
import co.icanteach.android.deeplinktester.settings.items.ShareTheApp

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
) {

    val scrollableState = rememberScrollState()
    SettingsScreen(scrollableState)
}

@Composable
fun SettingsScreen(
    scrollableState: ScrollState,
) {

    Scaffold(
        topBar = {
            HomeTopAppBar(
                appBarTitle = stringResource(id = R.string.settings_page_title)
            ) {}
        }
    ) { contentPaddingValue ->
        Column(
            modifier = Modifier
                .verticalScroll(scrollableState)
                .padding(contentPaddingValue)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            NavigateHistory()
            ShareTheApp()
            RateTheApp()
            AppVersion()
        }
    }

}