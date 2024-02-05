package com.malisoftware.restaurant.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.local.OrderStatus
import com.malisoftware.local.mappers.toBusinessEntity
import com.malisoftware.local.mappers.toItemEntity
import com.malisoftware.local.repository.LocalRepository
import com.malisoftware.model.BusinessData
import com.malisoftware.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import java.time.LocalDateTime
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val roomDb : LocalRepository
): ViewModel() {

    suspend fun insertOrder(
        item: Items,
        restaurant: BusinessData,
        instruction: String
    ) {
        val order = ItemOrderEntity(
            restaurant = restaurant.toBusinessEntity(),
            items = listOf(item.toItemEntity()),
            specialInstruction = instruction,
            dateTime = LocalDateTime.now().toString(),
            status = OrderStatus.PENDING
        )

        roomDb.insertOrder(order).collect{
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

    suspend fun deleteOrder(order: ItemOrderEntity) = roomDb.deleteOrder(order).collect{
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