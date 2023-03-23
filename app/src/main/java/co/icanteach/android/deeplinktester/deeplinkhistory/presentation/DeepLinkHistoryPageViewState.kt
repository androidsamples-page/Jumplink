package co.icanteach.android.deeplinktester.deeplinkhistory.presentation

import co.icanteach.android.deeplinktester.DeepLinkItem

data class DeepLinkHistoryPageViewState constructor(
    val historyItems: List<DeepLinkItem> = emptyList(),
    private val showHistoryOrEmptyState: Boolean = false
) {

    fun onUpdateHistoryItem(newValue: List<DeepLinkItem>): DeepLinkHistoryPageViewState {
        return copy(historyItems = newValue, showHistoryOrEmptyState = true)
    }

    fun shouldShowContent() = showHistoryOrEmptyState
}