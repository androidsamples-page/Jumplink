package co.icanteach.android.deeplinktester.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.android.deeplinktester.R
import co.icanteach.android.deeplinktester.settings.items.AppVersion
import co.icanteach.android.deeplinktester.settings.items.DarkModeApp
import co.icanteach.android.deeplinktester.settings.items.NavigateHistory
import co.icanteach.android.deeplinktester.settings.items.RateTheApp
import co.icanteach.android.deeplinktester.settings.items.ShareTheApp

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateHistoryScreenClicked: () -> Unit,
) {

    val uiState = viewModel.uiState.collectAsState().value

    SettingsScreenResult(
        uiState = uiState,
        onNavigateHistoryScreenClicked = {
            onNavigateHistoryScreenClicked.invoke()
        },
        onDarkThemeChanged = { isSelected ->
            viewModel.onEvent(SettingsScreenActions.OnDarkThemeChanged(isDarkThemeEnabled = isSelected))
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenResult(
    uiState: SettingsScreenUiState,
    onNavigateHistoryScreenClicked: () -> Unit,
    onDarkThemeChanged: (Boolean) -> Unit,
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
                .padding(contentPaddingValue)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            NavigateHistory(onNavigateHistoryScreenClicked = {
                onNavigateHistoryScreenClicked.invoke()
            })
            DarkModeApp(
                isDarkThemeSelected = uiState.isDarkThemeSelected,
                onDarkThemeChanged = { isSelected ->
                    onDarkThemeChanged.invoke(isSelected)
                })
            ShareTheApp()
            RateTheApp()
            AppVersion()
        }
    }

}