package co.icanteach.android.deeplinktester.deeplinkhistory.domain

import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkDao
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkEntity
import java.util.UUID
import javax.inject.Inject
import java.util.Date

class SaveDeepLinkToHistoryUseCase @Inject constructor(
    private val deepLinkDao: DeepLinkDao
) {
    suspend fun saveItem(enteredDeepLink: String) {
        val entity = DeepLinkEntity(
            id = UUID.randomUUID().toString(),
            deepLink = enteredDeepLink,
            createdDate = Date()
        )
        deepLinkDao.insertAll(entity)
    }
}