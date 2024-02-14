package com.malisoftware.restaurant.viewModel

import androidx.lifecycle.ViewModel
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.components.uiEvent.UiEvent

import com.malisoftware.model.BusinessItems
import com.malisoftware.restaurant.uistates.RestaurantOrderState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class RestaurantOrderVM @Inject constructor(
    private val dataUseCase: DataUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(RestaurantOrderState())
    val state = _state.asStateFlow()


    private val _restaurantsItems = MutableStateFlow(emptyList<BusinessItems>())
    val restaurantsItems = _restaurantsItems.asStateFlow()
    
    
    suspend fun getRestaurantItems(restaurantId: String) = dataUseCase.getRestaurantItems(restaurantId).collect {
        when (it) {
            is UiEvent.Loading -> {
                _state.update { state -> state.copy(loading = true) }
            }
            is UiEvent.Success -> {
                val items = it.data!!.map { it.items }.flatten().map { it.items }.flatten()
                _state.update { state -> state.copy(
                    loading = false,
                    restaurantItems = it.data!!.map { it.items }.flatten(),
                    itemsInPromotion = items.filter { it.promotion != "" }.shuffled(Random(2))
                ) }
            }
            is UiEvent.Error -> {
                _state.update { state -> state.copy(loading = false) }
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
        _state.update { state -> state.copy(restaurantsItems = items.value.shuffled().take(1)) }
    }
    fun searchItems(query: String)  {
       val items = _state.value.restaurantItems
           .map { it.items }.flatten()
           .filter { it.title.contains(query, true) }
           .toList()
        _state.update { state -> state.copy(searchResults = items) }
    }

}