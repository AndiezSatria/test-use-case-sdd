package org.andiez.common.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import org.andiez.common.data.source.local.dao.AppDao
import org.andiez.common.data.source.local.entity.TransactionEntity
import org.andiez.common.data.source.local.entity.UserEntity

/**
 * Created by AndiezSatria on 17/04/2023.
 */

@Database(
    entities = [
        UserEntity::class,
        TransactionEntity::class,
    ],
    version = 3,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}