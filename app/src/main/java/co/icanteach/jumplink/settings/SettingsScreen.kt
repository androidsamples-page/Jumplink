package co.icanteach.jumplink.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.android.apps.jumplink.R
import co.icanteach.jumplink.settings.items.AppVersion
import co.icanteach.jumplink.settings.items.DarkModeApp
import co.icanteach.jumplink.settings.items.NavigateHistory
import co.icanteach.jumplink.settings.items.RateTheApp
import co.icanteach.jumplink.settings.items.ShareTheApp
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme

@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onNavigateHistoryScreenClicked: () -> Unit,
) {

    val uiState = viewModel.uiState.collectAsState().value

    SettingsScreenResult(
        uiState = uiState,
        onNavigateHistoryScreenClicked = onNavigateHistoryScreenClicked::invoke,
        onDarkThemeChanged = { isSelected ->
            viewModel.onEvent(
                SettingsScreenActions.OnDarkThemeChanged(isDarkThemeEnabled = isSelected)
            )
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
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(8.dp)
                )
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
            // ShareTheApp()
            // RateTheApp()
            AppVersion()
        }
    }

}

@ThemesPreview
@Composable
fun SettingsScreen_Preview() {

    val uiState = SettingsScreenUiState(
        isDarkThemeSelected = false
    )

    JumpLinkTheme {
        SettingsScreenResult(
            uiState = uiState,
            onNavigateHistoryScreenClicked = {},
            onDarkThemeChanged = {},
        )
    }
}
