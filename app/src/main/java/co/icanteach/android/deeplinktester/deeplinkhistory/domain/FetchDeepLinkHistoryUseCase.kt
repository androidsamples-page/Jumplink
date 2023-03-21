package co.icanteach.android.deeplinktester.deeplinkhistory.domain

import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkDao
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchDeepLinkHistoryUseCase @Inject constructor(
    private val deepLinkDao: DeepLinkDao
) {
    fun fetchDeepLinkHistory(): Flow<List<DeepLinkItem>> {
        return deepLinkDao.getAll().map { result ->
            result.map { it.asExternalModel() }
        }
    }
}