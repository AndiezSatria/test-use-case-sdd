package org.andiez.common.domain.model

import org.andiez.common.data.source.local.entity.UserEntity

/**
 * Created by AndiezSatria on 11/10/2023.
 */
data class User(
    val id: Int,
    val userName: String,
    val balance: Long,
)

fun User.toEntity(): UserEntity = UserEntity(
    id = this.id,
    userName = this.userName,
    balance = this.balance,
)
