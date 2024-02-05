package com.malisoftware.local.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecentlyViewedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val businessIds: List<String> = emptyList()
)
