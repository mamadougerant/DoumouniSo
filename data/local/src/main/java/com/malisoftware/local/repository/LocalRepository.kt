package com.malisoftware.local.repository


import androidx.lifecycle.asFlow
import com.malisoftware.local.db.AppDatabase
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.local.local.AddressEntity
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.local.RecentlyViewedEntity
import com.malisoftware.local.local.UserFavoritesEntity
import com.malisoftware.local.mappers.toRealmBusiness
import com.malisoftware.local.mappers.toRealmItems
import com.malisoftware.local.reamlModel.RealmItemOrder
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import java.time.LocalDateTime

class LocalRepository(
    private val appDatabase: AppDatabase,
    private val realm: Realm
) {
    private val ioDispatcher = Dispatchers.IO
    // Realm
    fun getAllCompletedOrder() = flow {
        emit(UiEvent.Loading())
        val result = realm
            .query<RealmItemOrder>()
            .asFlow()
            .map { it.list.toList() }
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun insertCompletedOrder(order: ItemOrderEntity, items: List<ItemsEntity>) = flow {
        emit(UiEvent.Loading())
        realm.write {
            val orderToInsert = RealmItemOrder().apply {
                this.restaurant = order.restaurant.toRealmBusiness()
                this.items.addAll(items.map { it.toRealmItems() })
                this.dateTime = LocalDateTime.now().toString()
                this.status = order.status.value
            }
            copyToRealm(orderToInsert, UpdatePolicy.ALL)
        }
        emit(UiEvent.Success("Order added"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)


    fun deleteCompletedOrder(order: ItemOrderEntity) = flow {
        emit(UiEvent.Loading())
        realm.write {
            val orderToDelete = realm.query<RealmItemOrder>("id == $0", order.id).find().firstOrNull()
            if (orderToDelete != null) {
               this.delete(orderToDelete)
            }
        }
        emit(UiEvent.Success("Order deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteAllCompletedOrder() = flow {
        emit(UiEvent.Loading())
        realm.write {
            deleteAll()
        }
        emit(UiEvent.Success("All orders deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun updateCompletedOrder(order: ItemOrderEntity) = flow {
        emit(UiEvent.Loading())
        realm.write {
            val orderToUpdate = realm.query<RealmItemOrder>("id == $0", order.id).find().firstOrNull()
            if (orderToUpdate != null) {
                orderToUpdate.status = order.status.value
                //orderToUpdate.items = order.items.map { it.toRealmItems() }
            }
        }
        emit(UiEvent.Success("Order updated"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    // ORDER

    fun getAllOrder() = flow {
        emit(UiEvent.Loading())
        val result = appDatabase.orderDao().getAll().asFlow()
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun orderCount() = appDatabase.orderDao().orderCount()

    fun insertOrder(order: ItemOrderEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderDao().insert(order)
        emit(UiEvent.Success("Order added"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun updateItemOrderEntity(order: ItemOrderEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderDao().upsert(order)
        emit(UiEvent.Success("Order updated"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteOrder(order: ItemOrderEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderDao().delete(order)
        emit(UiEvent.Success("Order deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteOrderById(id: String) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderDao().deleteById(id)
        emit(UiEvent.Success("Order deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteAllOrder() = flow {
        emit(UiEvent.Loading())
        appDatabase.orderDao().deleteAll()
        emit(UiEvent.Success("All orders deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)


    // ORDER ITEM

    fun getAllOrderByRestaurantId(restaurantId: String) = flow {
        emit(UiEvent.Loading())
        val result = appDatabase.orderItemDao().getAllOrderByRestaurantId(restaurantId).asFlow()
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun insertOrderItem(itemsEntity: ItemsEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderItemDao().insert(itemsEntity)
        emit(UiEvent.Success("Order item added"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteOrderItemByItemId(itemId: String) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderItemDao().deleteByItemId(itemId)
        emit(UiEvent.Success("Order item deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteOrderItemByRestaurantId(restaurantId: String) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderItemDao().deleteByRestaurantId(restaurantId)
        emit(UiEvent.Success("Order item deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteOrderItem(item: ItemsEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.orderItemDao().deleteItem(item)
        emit(UiEvent.Success("Order item deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)


    // Address


    fun insertAddress(address: AddressEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.address().insert(address)
        emit(UiEvent.Success("Address added"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteAddress(address: AddressEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.address().delete(address)
        emit(UiEvent.Success("Address deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun getAddress() = flow {
        emit(UiEvent.Loading())
        val result = appDatabase.address().getAddress().asFlow()
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun getAddressById(id: Long) = flow {
        emit(UiEvent.Loading())
        val result = appDatabase.address().getAddressById(id).asFlow()
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteAllAddress() = flow {
        emit(UiEvent.Loading())
        appDatabase.address().deleteAll()
        emit(UiEvent.Success("All addresses deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)


    // favorite


    fun getAllFavorite() = flow {
        emit(UiEvent.Loading())
        val result = appDatabase.favoriteDao().getFavorite().asFlow()
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun insertFavorite(favorite: UserFavoritesEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.favoriteDao().insert(favorite)
        emit(UiEvent.Success("Favorite added"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteFavorite(favorite: UserFavoritesEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.favoriteDao().delete(favorite)
        emit(UiEvent.Success("Favorite deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun deleteAllFavorite() = flow {
        emit(UiEvent.Loading())
        appDatabase.favoriteDao().deleteAll()
        emit(UiEvent.Success("All favorites deleted"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)


    // recent view


    fun getRecentView() = flow {
        emit(UiEvent.Loading())
        val result = appDatabase.recentlyViewedDao().getRecentlyViewed()
        if (result.value?.size!! > 5) {
            appDatabase.recentlyViewedDao().delete(result.value!!.last())
        }
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun insertRecentView(recentView: RecentlyViewedEntity) = flow {
        emit(UiEvent.Loading())
        appDatabase.recentlyViewedDao().insert(recentView)
        emit(UiEvent.Success("Recent view added"))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)


}

