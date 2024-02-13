package com.malisoftware.restaurant.viewModel

import androidx.lifecycle.ViewModel
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.components.uiEvent.UiEvent

import com.malisoftware.model.BusinessItems
import com.malisoftware.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class RestaurantOrderVM @Inject constructor(
    private val dataUseCase: DataUseCase
) : ViewModel() {
    
    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()
    
    private val _restaurantItems = MutableStateFlow(emptyList<BusinessItems>())
    val restaurantItems = _restaurantItems.asStateFlow()

    private val _itemsInPromotion = MutableStateFlow(emptyList<Items>())
    val itemsInPromotion = _itemsInPromotion.asStateFlow()


    private val _restaurantsItems = MutableStateFlow(emptyList<BusinessItems>())
    val restaurantsItems = _restaurantsItems.asStateFlow()
    
    
    suspend fun getRestaurantItems(restaurantId: String) = dataUseCase.getRestaurantItems(restaurantId).collect {
        when (it) {
            is UiEvent.Loading -> {
                _loading.value = true
            }
            is UiEvent.Success -> {
                _restaurantItems.value = it.data!!
                val items = it.data!!.map { it.items }.flatten().map { it.items }.flatten()
                _itemsInPromotion.value = items.filter { it.promotion != "" }.shuffled(Random(2))
                _loading.value = false
            }
            is UiEvent.Error -> {
                _loading.value = false
            }
        }
    }

    suspend fun getRestaurantsItems(restaurantId: List<String>){
        val items = MutableStateFlow(emptyList<BusinessItems>())
        restaurantId.forEach {
            dataUseCase.getRestaurantItems(it).collect {
                when (it) {
                    is UiEvent.Loading -> {
                    }
                    is UiEvent.Success -> {
                        items.value = items.value + it.data!!
                    }
                    is UiEvent.Error -> {
                    }
                }
            }
        }
        _restaurantsItems.value = items.value.shuffled().take(1)
    }


}