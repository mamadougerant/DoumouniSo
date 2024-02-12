package com.malisoftware.cart

import android.util.Log
import androidx.lifecycle.ViewModel
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.local.RecentlyViewedEntity
import com.malisoftware.local.mappers.toItemEntity
import com.malisoftware.local.repository.LocalRepository
import com.malisoftware.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class CartRoomViewModel @Inject constructor(
    private val roomDb : LocalRepository
): ViewModel() {

    private val _orders = MutableStateFlow<List<ItemOrderEntity>>(emptyList())
    val orders = _orders.asStateFlow()

    private val _items = MutableStateFlow<List<ItemsEntity>>(emptyList())
    val items = _items.asStateFlow()

    suspend fun getOrders() = roomDb.getAllOrder().collect{
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                it.data?.collectLatest { value ->
                    Log.d("CartRoomViewModel", "Success ${value}")
                    _orders.value = value
                }
                //_orders.value = it.data?.value!!
            }
            is UiEvent.Error -> {
                //Do something
            }
        }
    }

    suspend fun insertOrderItem(quantity: Int, item: Items, restaurantId: String ) {
        if (restaurantId.isEmpty()) return
        if (quantity == 0) {
            deleteOrderItem(item.toItemEntity(restaurantId))
            return
        }
        val orderItem = item.toItemEntity(restaurantId).copy(quantity = quantity)
        roomDb.insertOrderItem(orderItem).collect {}
    }

    suspend fun deleteOrder(order: ItemOrderEntity, id: String) {
        roomDb.deleteOrder(order).collect{}
        deleteItems(id)

    }

    suspend fun deleteOrderItem (itemsEntity: ItemsEntity) = roomDb.deleteOrderItem(itemsEntity).collect{}

    suspend fun updateItemOrderEntity(
        itemOrderEntity: ItemOrderEntity
    ) = roomDb.updateItemOrderEntity(itemOrderEntity).collect {}



    suspend fun getAllOrderByRestaurantId (restaurantId: String) = roomDb.getAllOrderByRestaurantId(restaurantId).collect{
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                it.data?.collectLatest { value ->
                    Log.d("CartRoomViewModel", "Success ${value}")
                    _items.value = value
                }
                //_orders.value = it.data?.value!!
            }
            is UiEvent.Error -> {
                //Do something
            }
        }
    }

    suspend fun deleteOrderItemByItemId(itemId: String) = roomDb.deleteOrderItemByItemId(itemId)


    suspend fun deleteItems(restaurantId: String) = roomDb.deleteOrderItemByRestaurantId(restaurantId)

    suspend fun insertRecentLyViewed(business: RecentlyViewedEntity) = roomDb.insertRecentView(business)

    suspend fun insertCompletedOrder(order: ItemOrderEntity,itemsEntity: List<ItemsEntity>) =
        roomDb.insertCompletedOrder(order,itemsEntity).collect{
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                // TODO Fectch the id and send the order with that id
                //Do something
            }
            is UiEvent.Error -> {
                //Do something
            }
        }
    }

    suspend fun deleteAllCompletedOrder() = roomDb.deleteAllCompletedOrder().collect{
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                //Do something
            }
            is UiEvent.Error -> {
                //Do something
            }
        }
    }

    suspend fun updateCompletedOrder(order: ItemOrderEntity) = roomDb.updateCompletedOrder(order).collect{
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                //Do something
            }
            is UiEvent.Error -> {
                //Do something
            }
        }
    }


}