package co.icanteach.android.deeplinktester.deeplinkhistory.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(entities = [DeepLinkEntity::class], version = 1)
@TypeConverters(DateTypeConverters::class)
abstract class DeepLinkDatabase : RoomDatabase() {
    abstract fun deepLinkDao(): DeepLinkDao
}