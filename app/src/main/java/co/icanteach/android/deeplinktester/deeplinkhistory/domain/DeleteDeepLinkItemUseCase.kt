package co.icanteach.android.deeplinktester.deeplinkhistory.domain

import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkDao
import javax.inject.Inject

class DeleteDeepLinkItemUseCase @Inject constructor(
    private val deepLinkDao: DeepLinkDao
) {
    suspend fun deleteItem(deepLinkItem: DeepLinkItem) {
        deepLinkDao.delete(deepLinkId = deepLinkItem.id)
    }

    suspend fun deleteAll() {
        deepLinkDao.deleteAll()
    }
}