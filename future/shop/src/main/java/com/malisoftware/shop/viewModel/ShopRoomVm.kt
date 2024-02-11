package com.malisoftware.shop.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.ItemsEntity
import com.malisoftware.local.local.OrderStatus
import com.malisoftware.local.local.RecentlyViewedEntity
import com.malisoftware.local.local.UserFavoritesEntity
import com.malisoftware.local.mappers.toBusinessEntity
import com.malisoftware.local.repository.LocalRepository
import com.malisoftware.model.BusinessData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class ShopRoomVm  @Inject constructor(
    private val roomDb: LocalRepository
): ViewModel() {
    private val _items = MutableStateFlow<List<ItemsEntity>>(emptyList())
    val items = _items.asStateFlow()

    private val _orders = MutableStateFlow<List<ItemOrderEntity>>(emptyList())
    val orders = _orders.asStateFlow()

    private val _recentlyViewed = MutableStateFlow<List<RecentlyViewedEntity>>(emptyList())
    val recentlyViewed = _recentlyViewed.asStateFlow()

    private val _favorites = MutableStateFlow<List<UserFavoritesEntity>>(emptyList())
    val favorite = _favorites.asStateFlow()

    suspend fun getOrders() = roomDb.getAllOrder().collect{
        Log.d("RoomViewModel1", "getorders")
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                it.data?.collect { value ->
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

    suspend fun getAllOrderByShopId(shopId: String) = roomDb.getAllOrderByRestaurantId(shopId).collect{
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                it.data?.collectLatest { value ->
                    _items.value = value
                }
            }
            is UiEvent.Error -> {
                //Do something
            }
        }
    }

    suspend fun insertOrder(
        shopData: BusinessData,
        id: String,
    ) {
        val order = ItemOrderEntity(
            restaurant = shopData.toBusinessEntity(),
            id = id,
            status = OrderStatus.PENDING
        )

        roomDb.updateItemOrderEntity(order).collect{
            when(it){
                is UiEvent.Loading -> {
                    Log.d("RoomViewModel", "Loading")
                }
                is UiEvent.Success -> {
                    Log.d("RoomViewModel", "Success")
                }
                is UiEvent.Error -> {
                    Log.d("RoomViewModel", "Error")
                }
            }
        }
    }
    suspend fun insertOrderItem(item: ItemsEntity,) = roomDb.insertOrderItem(item).collect{
        when(it){
            is UiEvent.Loading -> {
                Log.d("RoomViewModel", "Loading")
            }
            is UiEvent.Success -> {
                Log.d("RoomViewModel", "Success")
            }
            is UiEvent.Error -> {
                Log.d("RoomViewModel", "Error")
            }
        }
    }


    suspend fun getFavorites() {
        Log.d("RoomViewModel1", "getFavorites")
        roomDb.getAllFavorite().collect{

            when(it){
                is UiEvent.Loading -> {
                    Log.d("RoomViewModel1", "Loading")
                }
                is UiEvent.Success -> {
                    it.data?.collect { value ->
                        Log.d("RoomViewModel1", "Success ${value}")
                        _favorites.value = value
                    }
                }
                is UiEvent.Error -> {
                    Log.d("RoomViewModel1", "Error")
                }
            }
        }
    }


    suspend fun insertFavorite(business: BusinessData) {
        val userFavorite = UserFavoritesEntity(
            primaryKey = business.id,
            favoriteBusiness = business.toBusinessEntity()
        )
        roomDb.insertFavorite(userFavorite).collect{
            when(it){
                is UiEvent.Loading -> {
                    Log.d("RoomViewModel1", "Loading")
                }
                is UiEvent.Success -> {
                    Log.d("RoomViewModel1", "Success")
                }
                is UiEvent.Error -> {
                    Log.d("RoomViewModel1", "Error")
                }
            }
        }
    }

    suspend fun deleteFavorite(favorite: BusinessData) {
        val userFavorite = UserFavoritesEntity(
            primaryKey = favorite.id,
            favoriteBusiness = favorite.toBusinessEntity()
        )
        roomDb.deleteFavorite(userFavorite).collect{

        }
    }
}