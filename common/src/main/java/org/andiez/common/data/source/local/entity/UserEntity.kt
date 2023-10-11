package org.andiez.common.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Entity(tableName = "UserTable")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val userName: String,
    val balance: Long,
)