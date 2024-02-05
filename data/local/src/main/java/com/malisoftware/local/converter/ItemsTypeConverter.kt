package com.malisoftware.local.converter



import androidx.room.TypeConverter
import com.malisoftware.local.local.ItemsEntity
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemsTypeConverter {
    @TypeConverter
    fun fromString(value: String?): List<ItemsEntity>? {
        return value?.let {
            Gson().fromJson(it, object : TypeToken<List<ItemsEntity>>() {}.type)
        }
    }

    @TypeConverter
    fun toString(value: List<ItemsEntity>?): String? {
        return value?.let {
            Gson().toJson(it)
        }
    }
}
