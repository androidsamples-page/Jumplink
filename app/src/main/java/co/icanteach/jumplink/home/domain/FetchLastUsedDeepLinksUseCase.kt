package co.icanteach.jumplink.home.domain

import co.icanteach.jumplink.DeepLinkItem
import co.icanteach.jumplink.deeplinkhistory.cache.DeepLinkDao
import co.icanteach.jumplink.deeplinkhistory.cache.asExternalModel
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