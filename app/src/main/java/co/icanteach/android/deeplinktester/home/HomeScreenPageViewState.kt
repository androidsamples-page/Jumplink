package co.icanteach.android.deeplinktester.home

import co.icanteach.android.deeplinktester.DeepLinkItem

data class HomeScreenPageViewState constructor(
    val enteredContent: String = "",
    val historyItems: List<DeepLinkItem> = emptyList(),
    val showHistoryOrEmptyState: Boolean = false
) {

    fun onClearEnteredContent(): HomeScreenPageViewState {
        return copy(enteredContent = "")
    }

    fun onEnteredContent(newValue: String): HomeScreenPageViewState {
        return copy(enteredContent = newValue.trim())
    }

    fun onUpdateHistoryItem(newValue: List<DeepLinkItem>): HomeScreenPageViewState {
        return copy(historyItems = newValue, showHistoryOrEmptyState = true)
    }
}