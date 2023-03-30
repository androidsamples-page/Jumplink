package co.icanteach.jumplink.deeplinkhistory.cache

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import co.icanteach.jumplink.DeepLinkItem
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

/**
 * Local representation of [DeepLinkItem]
 */

@Entity(tableName = "saved_deeplinks", indices = [Index(value = ["deep_link"], unique = true)])
data class DeepLinkEntity constructor(
    @PrimaryKey val id: String,
    @ColumnInfo(name = "deep_link") val deepLink: String,
    @ColumnInfo(name = "created_date") val createdDate: Date? = null
)

/**
 * Converts the local model to the external model for deeplink
 * by domain modules.
 */
fun DeepLinkEntity.asExternalModel() = DeepLinkItem(
    id = id,
    deeplink = deepLink,
    createdDate = SimpleDateFormat(
        "dd/MM/yyyy",
        Locale.getDefault()
    ).format(createdDate ?: Date())
)