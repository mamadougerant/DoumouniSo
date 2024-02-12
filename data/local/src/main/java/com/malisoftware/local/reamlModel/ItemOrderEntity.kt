package com.malisoftware.local.reamlModel

import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.OrderStatus
import io.realm.kotlin.ext.backlinks
import io.realm.kotlin.ext.realmSetOf
import io.realm.kotlin.query.RealmResults
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.RealmSet
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class RealmItemOrder : RealmObject {
    @PrimaryKey
    var id: ObjectId = ObjectId()
    var restaurant: RealmBusiness? = null
    var items: RealmSet<RealmItems> = realmSetOf()
    var specialInstruction: String = ""
    var dateTime: String = ""
    var status: String = OrderStatus.UNKNOWN.value
}

