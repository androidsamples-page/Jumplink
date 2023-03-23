package co.icanteach.android.deeplinktester.home

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.FakeDeepLinkItemFactory
import co.icanteach.android.deeplinktester.home.composables.HomeScreenStateWithDeepLinkHistory
import co.icanteach.android.deeplinktester.home.composables.HomeScreenStateWithNoDeepLinkHistory
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = hiltViewModel(),
    onSettingsItemClicked: () -> Unit,
) {
    val currentContext = LocalContext.current
    val uiState = viewModel.uiState.collectAsState().value

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateDeepLinkContent -> {
                    onNavigateDeepLinkContent(
                        context = currentContext,
                        deepLinkContent = event.deepLinkContent
                    )
                }
            }
        }
    }

    HomeScreenResult(
        uiState = uiState,
        onTestDeeplinkClicked = {
            viewModel.onAction(HomeScreenActions.TestEnteredContent)
        },
        onClearDeeplinkClicked = {
            viewModel.onAction(HomeScreenActions.ClearEnteredContent)
        },
        onEnteredContent = { enteredContent ->
            viewModel.onAction(HomeScreenActions.EnteredContent(enteredContent))
        },
        onTestDeeplinkFromHistoryClicked = { deepLinkItem ->
            viewModel.onAction(HomeScreenActions.TestHistoryItemContent(deepLinkItem))
        },
        onSettingsItemClicked = {
            onSettingsItemClicked.invoke()
        }
    )
}

@Composable
fun HomeScreenResult(
    uiState: HomeScreenPageViewState,
    onEnteredContent: (String) -> Unit,
    onTestDeeplinkClicked: () -> Unit,
    onClearDeeplinkClicked: () -> Unit,
    onTestDeeplinkFromHistoryClicked: (DeepLinkItem) -> Unit,
    onSettingsItemClicked: () -> Unit,
) {

    if (uiState.shouldShowContent()) {
        if (uiState.historyItems.isEmpty()) {
            HomeScreenStateWithNoDeepLinkHistory(
                enteredContent = uiState.enteredContent,
                onTestDeeplinkClicked = {
                    onTestDeeplinkClicked.invoke()
                },
                onClearDeeplinkClicked = {
                    onClearDeeplinkClicked.invoke()
                },
                onEnteredContent = { enteredContent ->
                    onEnteredContent.invoke(enteredContent)
                },
                onSettingsItemClicked = {
                    onSettingsItemClicked.invoke()
                }
            )
        } else {
            HomeScreenStateWithDeepLinkHistory(
                enteredContent = uiState.enteredContent,
                onTestDeeplinkClicked = {
                    onTestDeeplinkClicked.invoke()
                },
                onClearDeeplinkClicked = {
                    onClearDeeplinkClicked.invoke()
                },
                onEnteredContent = { enteredContent ->
                    onEnteredContent.invoke(enteredContent)
                },
                historyDeepLinkItems = uiState.historyItems,
                onTestDeeplinkFromHistoryClicked = { deepLinkItem ->
                    onTestDeeplinkFromHistoryClicked.invoke(deepLinkItem)
                },
                onSettingsItemClicked = {
                    onSettingsItemClicked.invoke()
                }
            )
        }
    }
}

@Composable
@ThemesPreview
fun HomeScreenResultWithDeepLinkHistory_Preview() {
    JumpLinkTheme {
        val uiState = HomeScreenPageViewState(
            enteredContent = "DeepLink",
            historyItems = FakeDeepLinkItemFactory.createDeepLinkItems()
        )
        HomeScreenResult_PreviewTemplate(uiState)
    }
}

@Composable
@ThemesPreview
fun HomeScreenResultWithNoDeepLinkHistory_Preview() {
    JumpLinkTheme {
        val uiState = HomeScreenPageViewState(
            enteredContent = "DeepLink",
            historyItems = emptyList()
        )

        HomeScreenResult_PreviewTemplate(uiState)
    }
}

@Composable
fun HomeScreenResult_PreviewTemplate(
    uiState: HomeScreenPageViewState
) {
    HomeScreenResult(
        uiState = uiState,
        onTestDeeplinkClicked = {},
        onClearDeeplinkClicked = {},
        onEnteredContent = {},
        onTestDeeplinkFromHistoryClicked = {},
        onSettingsItemClicked = {},
    )
}

fun onNavigateDeepLinkContent(
    deepLinkContent: String,
    context: Context
) {
    val intent = Intent(Intent.ACTION_VIEW)
    intent.data = Uri.parse(deepLinkContent)
    ContextCompat.startActivity(context, intent, null)
}