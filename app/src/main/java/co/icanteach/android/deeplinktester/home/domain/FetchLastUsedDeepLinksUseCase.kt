package co.icanteach.android.deeplinktester.home.domain

import co.icanteach.android.deeplinktester.DeepLinkItem
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkDao
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.asExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FetchLastUsedDeepLinksUseCase @Inject constructor(
    private val deepLinkDao: DeepLinkDao
) {
    fun fetchDeepLinkHistory(): Flow<List<DeepLinkItem>> {
        return deepLinkDao
            .getLastInsertedDeepLinks()
            .map { result ->
                result.map { it.asExternalModel() }
            }
    }
}