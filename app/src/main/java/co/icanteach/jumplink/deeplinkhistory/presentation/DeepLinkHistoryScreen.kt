package co.icanteach.jumplink.deeplinkhistory.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import co.icanteach.android.apps.jumplink.R
import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.FakeDeepLinkItemFactory
import co.icanteach.jumplink.home.onNavigateDeepLinkContent
import core.libraries.design.preview.ThemesPreview
import core.libraries.design.theme.JumpLinkTheme
import kotlinx.coroutines.flow.collectLatest

@Composable
fun DeepLinkHistoryScreen(
    viewModel: DeepLinkHistoryViewModel = hiltViewModel(),
) {

    val uiState = viewModel.uiState.collectAsState().value
    val currentContext = LocalContext.current

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

    DeepLinkHistoryResultScreen(
        uiState = uiState,
        onClearHistoryClicked = {
            viewModel.onEvent(DeepLinkHistoryActions.ClearAllHistory)
        },
        onDeleteDeeplinkItemClicked = { deepLinkItem ->
            viewModel.onEvent(DeepLinkHistoryActions.ClearHistoryItemContent(deepLinkItem))
        },
        onTestDeeplinkClicked = { deepLinkItem ->
            viewModel.onEvent(DeepLinkHistoryActions.TestHistoryItemContent(deepLinkItem))
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DeepLinkHistoryResultScreen(
    uiState: DeepLinkHistoryPageViewState,
    onClearHistoryClicked: () -> Unit,
    onTestDeeplinkClicked: (DeepLinkItem) -> Unit,
    onDeleteDeeplinkItemClicked: (DeepLinkItem) -> Unit,
) {

    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    stringResource(id = R.string.history_page_title),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.surfaceColorAtElevation(16.dp)
            ),
            actions = {
                if (uiState.historyItems.isNotEmpty()) {
                    IconButton(
                        onClick = onClearHistoryClicked::invoke
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Delete,
                            contentDescription = stringResource(id = R.string.history_page_delete_all)
                        )
                    }
                }
            }
        )
    }) { paddingValue ->
        if (uiState.shouldShowContent()) {
            if (uiState.historyItems.isEmpty()) {
                DeepLinkHistoryNoResult()
            } else {
                DeepLinkHistoryWithResult(
                    contentPaddingValue = paddingValue,
                    historyItems = uiState.historyItems,
                    onDeleteDeeplinkItemClicked = onDeleteDeeplinkItemClicked::invoke,
                    onTestDeeplinkClicked = onTestDeeplinkClicked::invoke
                )
            }
        }
    }
}

@ThemesPreview
@Composable
fun DeepLinkHistoryResultScreen_Preview() {
    val uiState = DeepLinkHistoryPageViewState()
    uiState.onUpdateHistoryItem(FakeDeepLinkItemFactory.createDeepLinkItems())

    JumpLinkTheme {
        DeepLinkHistoryResultScreen(
            uiState,
            onClearHistoryClicked = {},
            onTestDeeplinkClicked = {},
            onDeleteDeeplinkItemClicked = {}
        )
    }
}