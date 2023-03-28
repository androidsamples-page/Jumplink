package co.icanteach.jumplink.deeplinkhistory.presentation

import co.icanteach.jumplink.DeepLinkItem

sealed interface DeepLinkHistoryActions {
    object ClearAllHistory : DeepLinkHistoryActions
    data class TestHistoryItemContent(val item: DeepLinkItem) : DeepLinkHistoryActions
    data class ClearHistoryItemContent(val item: DeepLinkItem) : DeepLinkHistoryActions
}