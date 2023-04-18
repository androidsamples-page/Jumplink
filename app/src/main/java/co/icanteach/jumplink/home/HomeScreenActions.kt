package co.icanteach.jumplink.home

import co.icanteach.jumplink.DeepLinkItem

sealed interface HomeScreenActions {
    data class EnteredContent(val value: String) : HomeScreenActions
    object OpenAppClicked : HomeScreenActions
    object ClearEnteredContent : HomeScreenActions
    object PasteContentClipboard : HomeScreenActions
    data class TestHistoryItemContent(val item: DeepLinkItem) : HomeScreenActions
}