package com.malisoftware.restaurant.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.malisoftware.components.constants.FilterConstant
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Sponsors
import com.malisoftware.model.Store
import com.malisoftware.model.BusinessData
import com.malisoftware.restaurant.uistates.RestaurantVmState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.update
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val dataUseCase: DataUseCase
): ViewModel(){

    private val _vmState = MutableStateFlow(RestaurantVmState())
    val vmState = _vmState.asStateFlow()


    private val _restaurantLoading = MutableStateFlow(true)
    private val _restaurantCategoryLoading = MutableStateFlow(true)
    val loading = _restaurantLoading.asStateFlow().combine(_restaurantCategoryLoading.asStateFlow()) { restaurantLoading, restaurantCategoryLoading ->
        restaurantLoading || restaurantCategoryLoading
    }


    suspend fun fetchAllData(){
        getSponsors()
        getRestaurantCategoryList()
        getRestaurantList()
        getSponsoredRestaurant()
        getDiscountedRestaurant()
        getPopularRestaurant()
    }


    fun getSponsoredRestaurant(){
        val sponsoredRestaurant = _vmState.value.restaurantList.filter { it.sponsored && it.isOpen }
        val twoSponsoredRestaurant = sponsoredRestaurant.shuffled(Random(1)).take(2)
        _vmState.update { state -> state.copy(sponsorRestaurantList = twoSponsoredRestaurant) }
    }

    fun getPopularRestaurant(){
        val popularRestaurantByOrder = _vmState.value.restaurantList.filter { it.isOpen }.maxBy { it.orderCount }
        val popularRestaurantByRating = _vmState.value.restaurantList.filter { it.isOpen }.maxBy { it.feedback.toDouble() }
        //val sponsoredRestaurant = _restaurantList.value.filter { it.sponsored }.shuffled(Random(1)).take(1)

        _vmState.update { state -> state.copy(
            popularRestaurantList = listOf(popularRestaurantByOrder, popularRestaurantByRating)
        )}
    }

    fun getDiscountedRestaurant() {
        _vmState.update { state -> state.copy(
            promotionRestaurantList = _vmState.value.restaurantList.filter { it.isOpen && it.promotion != "" }
        )}
    }


    suspend fun getSponsors() = dataUseCase.getSponsors().collect {
        Log.d("RestaurantViewModel", "getSponsors: $it")
        when(it){
            is UiEvent.Loading -> {

            }
            is UiEvent.Success -> {
                _vmState.update { state -> state.copy(sponsors = it.data!!.shuffled(Random(1)))}
            }
            is UiEvent.Error -> {
            }
        }
    }



    suspend fun getRestaurantList() = dataUseCase.getRestaurantList().collect {
        when(it){
            is UiEvent.Loading -> {
                _restaurantLoading.value = true
            }
            is UiEvent.Success -> {
                _vmState.update { state -> state.copy(restaurantList = it.data!!) }
                _restaurantLoading.value = false
            }
            is UiEvent.Error -> {
                _restaurantLoading.value = false
            }
        }
    }

    suspend fun getRestaurant(id: String) = dataUseCase.getRestaurant(id).collect {
        when(it){
            is UiEvent.Loading -> {
            }
            is UiEvent.Success -> {
                _vmState.update { state -> state.copy(restaurantById = it.data!!) }
            }
            is UiEvent.Error -> {
            }
        }
    }


    suspend fun getRestaurantListByCategory(category: String) = dataUseCase.getRestaurantListByCategory(category).collect {
        when(it){
            is UiEvent.Loading -> {

            }
            is UiEvent.Success -> {
                _vmState.update { state -> state.copy(restaurantsByCategory = it.data?.ifEmpty { null })}
                when{
                    _vmState.value.filterList.contains(FilterConstant.OPEN_NOW.title) -> filterOpenNow()
                    _vmState.value.filterList.contains(FilterConstant.PROMOTIONS.title) -> filterPromotions()
                    _vmState.value.filterList.contains(FilterConstant.PRICE.title) -> filterPrice(_vmState.value.minPrice, _vmState.value.maxPrice)
                    _vmState.value.filterList.contains(FilterConstant.RATING.title) -> filterRating(_vmState.value.minRating, _vmState.value.maxRating)
                    _vmState.value.filterList.contains(FilterConstant.DELIVERY_TIME.title) -> filterDeliveryTime(_vmState.value.maxDeliveryTime, _vmState.value.maxDeliveryTime)
                    _vmState.value.filterList.contains(FilterConstant.DELIVERY_FEE.title) -> filterDeliveryFee(_vmState.value.minDeliveryFee, _vmState.value.maxDeliveryFee)
                }
            }
            is UiEvent.Error -> {
                _vmState.update { state -> state.copy(restaurantsByCategory = null) }
            }
        }
    }

    fun setRestaurantsByCategory(filterList: List<BusinessData>) {
        _vmState.update { state -> state.copy(restaurantsByCategory = filterList) }
    }



    suspend fun getRestaurantCategoryList() = dataUseCase.getRestaurantCategoryList().collect {
        when(it){
            is UiEvent.Loading -> {
                _restaurantCategoryLoading.value = true
            }
            is UiEvent.Success -> {
                _vmState.update { state -> state.copy(restaurantCategoryList = it.data!!)}
                _restaurantCategoryLoading.value = false
            }
            is UiEvent.Error -> {
                _restaurantCategoryLoading.value = false
            }
        }
    }

    fun closeSheet() {
        _vmState.update { state -> state.copy(openSheet = false) }
    }
    fun addFilter(filter: String){
        if (_vmState.value.filterList.contains(filter)){
            _vmState.update { state -> state.copy(
                filterList = state.filterList.filter { it != filter },
                openSheet = false
            ) }
            cancelFilter()
        }else{
            _vmState.update { state -> state.copy(filterList = state.filterList + filter) }
            when(filter){
                FilterConstant.OPEN_NOW.title -> filterOpenNow()
                FilterConstant.PROMOTIONS.title -> filterPromotions()
            }
            _vmState.update { state -> state.copy(openSheet = true) }
        }
    }
    fun removeFilter(filter: String){
        _vmState.update { state -> state.copy(filterList = state.filterList.filter { it != filter }) }
        _vmState.update { state -> state.copy(openSheet = false) }
    }

    private fun filterOpenNow(){
        if (_vmState.value.restaurantsByCategory != null){
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantsByCategory!!.filter { it.isOpen }
            ) }
        }else {
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantList.filter { it.isOpen }
            ) }
        }
    }


    private fun filterPromotions(){
        if (_vmState.value.restaurantsByCategory != null){
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantsByCategory!!.filter { it.promotion.isNotEmpty() }
            )}
        }else {
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantList.filter { it.promotion.isNotEmpty() }
            )}
        }
    }

    fun filterPrice(min: Double, max: Double){
        _vmState.update { state -> state.copy(minPrice = min, maxPrice = max)}
        if (_vmState.value.restaurantsByCategory != null){
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantsByCategory!!.filter { it.minPrice in min..max }
            )}
        }else {
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantList.filter { it.minPrice in min..max }
            )}
        }
    }

    fun filterRating(min: Double, max: Double){
        _vmState.update { state -> state.copy(minRating = min, maxRating = max)}
        if (_vmState.value.restaurantsByCategory != null){
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantsByCategory!!.filter { it.feedback.toDouble() in min..max }
            )}
        }else {
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantList.filter { it.feedback.toDouble() in min..max }
            )}
        }
    }

    fun filterDeliveryTime(min: Int, max: Int){
        _vmState.update { state -> state.copy(minDeliveryTime = min, maxDeliveryTime = max)}

        if (_vmState.value.restaurantsByCategory != null){
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantsByCategory!!.filter { it.deliveryTime.toInt() in min..max }
            )}
        }else {
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantList.filter { it.deliveryTime.toInt() in min..max }
            )}
        }
    }

    fun filterDeliveryFee(min: Double, max: Double){
        _vmState.update { state -> state.copy(minDeliveryFee = min, maxDeliveryFee = max)}
        if (_vmState.value.restaurantsByCategory != null){
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantsByCategory!!.filter { it.deliveryFee.toDouble() in min..max }
            )}
        }else {
            _vmState.update { state -> state.copy(restaurantsByCategory =
            _vmState.value.restaurantList.filter { it.deliveryFee.toDouble() in min..max }
            )}
        }
    }

    fun clearFilter(){
        _vmState.update { state -> state.copy(filterList = emptyList(), restaurantsByCategory = null) }
    }

    private fun cancelFilter(){
        _vmState.update { state -> state.copy(restaurantsByCategory = _vmState.value.restaurantList)}
        when{
            _vmState.value.filterList.contains(FilterConstant.OPEN_NOW.title) -> filterOpenNow()
            _vmState.value.filterList.contains(FilterConstant.PROMOTIONS.title) -> filterPromotions()
            _vmState.value.filterList.contains(FilterConstant.PRICE.title) -> filterPrice(_vmState.value.minPrice, _vmState.value.maxPrice)
            _vmState.value.filterList.contains(FilterConstant.RATING.title) -> filterRating(_vmState.value.minPrice, _vmState.value.maxPrice)
            _vmState.value.filterList.contains(FilterConstant.DELIVERY_TIME.title) -> filterDeliveryTime(_vmState.value.maxDeliveryTime, _vmState.value.maxDeliveryTime)
            _vmState.value.filterList.contains(FilterConstant.DELIVERY_FEE.title) -> filterDeliveryFee(_vmState.value.minDeliveryFee, _vmState.value.maxDeliveryFee)
            _vmState.value.filterList.isEmpty() -> clearFilter()
        }
    }




}