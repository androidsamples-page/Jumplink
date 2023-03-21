package co.icanteach.android.deeplinktester.deeplinkhistory

import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.FakeDeepLinkItemFactory
import javax.inject.Inject

class FetchDeepLinkHistory @Inject constructor() {

    fun fetchDeepLinkHistory(): List<DeepLinkItem> {
        return FakeDeepLinkItemFactory.createDeepLinkItems()
    }
}