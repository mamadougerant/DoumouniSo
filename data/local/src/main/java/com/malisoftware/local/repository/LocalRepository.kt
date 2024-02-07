package com.malisoftware.local.repository


import androidx.lifecycle.asFlow
import com.malisoftware.local.db.AppDatabase
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.local.local.AddressEntity
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.local.RecentlyViewedEntity
import com.malisoftware.local.local.UserFavoritesEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class LocalRepository(
    private val appDatabase: AppDatabase
) {
    private val ioDispatcher = Dispatchers.IO
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

    fun updateOrder(order: ItemOrderEntity) = flow {
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


    fun getAlFavorite() = flow {
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

