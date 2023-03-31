package co.icanteach.jumplink.home

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.ClipboardManager
import androidx.compose.ui.platform.LocalClipboardManager
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.FakeDeepLinkItemFactory
import co.icanteach.jumplink.home.composables.HomeScreenStateWithDeepLinkHistory
import co.icanteach.jumplink.home.composables.HomeScreenStateWithNoDeepLinkHistory
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
    val clipboardManager: ClipboardManager = LocalClipboardManager.current

    LaunchedEffect(key1 = true) {
        viewModel.eventFlow.collectLatest { event ->
            when (event) {
                is UiEvent.NavigateDeepLinkContent -> {
                    onNavigateDeepLinkContent(
                        context = currentContext,
                        deepLinkContent = event.deepLinkContent
                    )
                }
                UiEvent.PasteDeepLinkContent -> {
                    clipboardManager.getText()?.text?.let { enteredContent ->
                        viewModel.onAction(HomeScreenActions.EnteredContent(enteredContent))
                    }
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
        },
        onPasteContent = {
            viewModel.onAction(HomeScreenActions.PasteContentClipboard)
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
    onPasteContent: () -> Unit,
) {

    if (uiState.shouldShowContent()) {
        if (uiState.historyItems.isEmpty()) {
            HomeScreenStateWithNoDeepLinkHistory(
                enteredContent = uiState.enteredContent,
                onTestDeeplinkClicked = onTestDeeplinkClicked::invoke,
                onClearDeeplinkClicked = onClearDeeplinkClicked::invoke,
                onEnteredContent = onEnteredContent::invoke,
                onSettingsItemClicked = onSettingsItemClicked::invoke,
                onPasteContent = onPasteContent::invoke
            )
        } else {
            HomeScreenStateWithDeepLinkHistory(
                enteredContent = uiState.enteredContent,
                onTestDeeplinkClicked = onTestDeeplinkClicked::invoke,
                onClearDeeplinkClicked = onClearDeeplinkClicked::invoke,
                onEnteredContent = { enteredContent ->
                    onEnteredContent(enteredContent)
                },
                historyDeepLinkItems = uiState.historyItems,
                onTestDeeplinkFromHistoryClicked = onTestDeeplinkFromHistoryClicked::invoke,
                onSettingsItemClicked = onSettingsItemClicked::invoke,
                onPasteContent = onPasteContent::invoke
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
        onPasteContent = {},
    )
}

fun onNavigateDeepLinkContent(
    deepLinkContent: String,
    context: Context
) {
    try {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(deepLinkContent)
        ContextCompat.startActivity(context, intent, null)
    } catch (ex : ActivityNotFoundException) {
        /**
         * TODO https://github.com/androidsamples-page/Jumplink/issues/8
         */
    }
}