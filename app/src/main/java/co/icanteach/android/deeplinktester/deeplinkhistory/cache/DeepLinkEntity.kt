package co.icanteach.android.deeplinktester.deeplinkhistory.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import co.icanteach.android.deeplinktester.DeepLinkItem

/**
 * Local representation of [DeepLink]
 */

@Entity(tableName = "saved_deeplinks")
data class DeepLinkEntity constructor(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "deep_link") val deepLink: String,
)

/**
 * Converts the local model to the external model for deeplink
 * by domain modules.
 */
fun DeepLinkEntity.asExternalModel() = DeepLinkItem(
    id = id,
    deeplink = deepLink
)