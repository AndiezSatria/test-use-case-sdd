package org.andiez.common.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.andiez.common.data.source.local.dao.AppDao

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Database(
    entities = [],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun appDao(): AppDao
}