package org.andiez.common.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.andiez.common.domain.model.User

/**
 * Created by AndiezSatria on 11/10/2023.
 */

@Entity(tableName = "UserTable")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val userName: String,
    val balance: Long,
)

fun UserEntity.toDomain(): User = User(
    id = this.id,
    userName = this.userName,
    balance = this.balance,
)

fun List<UserEntity>.toDomains(): List<User> = this.map { entity ->
    User(
        id = entity.id,
        userName = entity.userName,
        balance = entity.balance,
    )
}