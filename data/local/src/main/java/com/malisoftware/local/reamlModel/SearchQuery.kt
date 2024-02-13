package com.malisoftware.local.reamlModel

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class SearchQuery : RealmObject {
    @PrimaryKey
    var id : ObjectId = ObjectId()
    var query : String = ""
    var isRestaurant : Boolean = false
}