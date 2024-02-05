package com.malisoftware.local.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class UserFavoritesEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val favoriteBusinessIds: List<String> = emptyList()
)
