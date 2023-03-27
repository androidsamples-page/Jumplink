package co.icanteach.android.deeplinktester.deeplinkhistory.cache

import androidx.room.TypeConverter
import java.util.Date

object DateTypeConverters {

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): Date? = if (null == value) null else Date(value)

    @TypeConverter
    @JvmStatic
    fun dateToTimestamp(date: Date?): Long? = date?.time
}