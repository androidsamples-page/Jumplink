package co.icanteach.android.deeplinktester.deeplinkhistory.presentation

import co.icanteach.android.deeplinktester.DeepLinkItem

sealed interface DeepLinkHistoryActions {
    object ClearAllHistory : DeepLinkHistoryActions
    data class TestHistoryItemContent(val item: DeepLinkItem) : DeepLinkHistoryActions
    data class ClearHistoryItemContent(val item: DeepLinkItem) : DeepLinkHistoryActions
}