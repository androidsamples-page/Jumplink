package co.icanteach.android.deeplinktester.home

import co.icanteach.android.deeplinktester.DeepLinkItem

sealed interface HomeScreenActions {
    data class EnteredContent(val value: String) : HomeScreenActions
    object TestEnteredContent : HomeScreenActions
    object ClearEnteredContent : HomeScreenActions
    object PasteContentClipboard : HomeScreenActions
    data class TestHistoryItemContent(val item: DeepLinkItem) : HomeScreenActions
}