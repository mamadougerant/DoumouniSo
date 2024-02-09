package com.malisoftware.local.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.malisoftware.model.BusinessData

@Entity
data class RecentlyViewedEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @Embedded
    val business: BusinessEntity = BusinessEntity(),
)
