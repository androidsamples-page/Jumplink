package co.icanteach.jumplink.deeplinkhistory.domain

import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.deeplinkhistory.cache.DeepLinkDao
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