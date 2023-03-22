package co.icanteach.android.deeplinktester.deeplinkhistory

import android.content.Context
import androidx.room.Room
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkDao
import co.icanteach.android.deeplinktester.deeplinkhistory.cache.DeepLinkDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

const val DB_NAME = "deeplink_tester_database"

@Module
@InstallIn(SingletonComponent::class)
abstract class DeepLinkDataModule {


    companion object {


        @Provides
        @Singleton
        fun provideDeepLinkDao(deepLinkDatabase: DeepLinkDatabase): DeepLinkDao {
            return deepLinkDatabase.deepLinkDao()
        }

        @Provides
        @Singleton
        fun provideDeepLinkDatabase(@ApplicationContext appContext: Context): DeepLinkDatabase {
            return Room.databaseBuilder(
                appContext, DeepLinkDatabase::class.java, DB_NAME
            ).build()
        }
    }
}