package co.icanteach.jumplink.deeplinkhistory.presentation

import co.icanteach.jumplink.DeepLinkItem

data class DeepLinkHistoryPageViewState constructor(
    val historyItems: List<DeepLinkItem> = emptyList(),
    private val showHistoryOrEmptyState: Boolean = false
) {

    fun onUpdateHistoryItem(newValue: List<DeepLinkItem>): DeepLinkHistoryPageViewState {
        return copy(historyItems = newValue, showHistoryOrEmptyState = true)
    }

    fun shouldShowContent() = showHistoryOrEmptyState
}