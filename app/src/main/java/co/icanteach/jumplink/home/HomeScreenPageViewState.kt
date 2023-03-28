package co.icanteach.jumplink.home

import co.icanteach.jumplink.DeepLinkItem

data class HomeScreenPageViewState constructor(
    val enteredContent: String = "",
    val historyItems: List<DeepLinkItem> = emptyList(),
    private val showHistoryOrEmptyState: Boolean = false
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

    fun shouldShowContent() = showHistoryOrEmptyState

    fun isEnteredContentValid(): Boolean {
        return enteredContent.isEmpty().not()
    }

}