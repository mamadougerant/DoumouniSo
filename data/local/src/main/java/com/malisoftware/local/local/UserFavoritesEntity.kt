package com.malisoftware.local.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserFavoritesEntity(
    @PrimaryKey()
    val primaryKey: String = "0",
    @Embedded
    val favoriteBusiness: BusinessEntity = BusinessEntity(),
)
