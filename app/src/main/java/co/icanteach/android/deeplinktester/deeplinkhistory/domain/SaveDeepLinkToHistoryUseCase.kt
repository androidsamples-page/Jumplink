package co.icanteach.android.deeplinktester.deeplinkhistory.domain

import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkDao
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkEntity
import java.util.UUID
import javax.inject.Inject

class SaveDeepLinkToHistoryUseCase @Inject constructor(
    private val deepLinkDao: DeepLinkDao
) {
    suspend fun saveItem(enteredDeepLink: String) {
        val entity = DeepLinkEntity(
            id = UUID.randomUUID().toString(),
            deepLink = enteredDeepLink
        )
        deepLinkDao.insertAll(entity)
    }
}