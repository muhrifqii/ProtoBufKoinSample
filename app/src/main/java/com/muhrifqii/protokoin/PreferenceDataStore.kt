package com.muhrifqii.protokoin

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.dataStoreFile
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import org.koin.core.annotation.Singleton

private const val DATASTORE_USER_SETTING_FILE = "user.xyz"

object DataStoreProvider {
  @Singleton
  fun provideUserSettingDatastore(context: Context): DataStore<UserPreferenceSetting> =
    DataStoreFactory.create(
      serializer = UserPreferenceSettingSerializer,
      produceFile = { context.dataStoreFile(DATASTORE_USER_SETTING_FILE) },
      corruptionHandler = ReplaceFileCorruptionHandler { userPreferenceSetting { } },
      scope = CoroutineScope(Dispatchers.IO + SupervisorJob())
    )
}
