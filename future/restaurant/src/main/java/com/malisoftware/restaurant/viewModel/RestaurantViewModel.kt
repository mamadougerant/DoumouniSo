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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val dataUseCase: DataUseCase
): ViewModel(){
    private val _sponsors = MutableStateFlow<List<Sponsors>>(emptyList())
    val sponsors = _sponsors.asStateFlow()

    private val _storeList = MutableStateFlow<List<Store>>((emptyList()))
    val storeList = _storeList.asStateFlow()

    private val _restaurantList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val restaurantList = _restaurantList.asStateFlow()

    private val _popularRestaurantList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val popularRestaurantList = _popularRestaurantList.asStateFlow()

    private val _promotionRestaurantList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val promotionRestaurantList = _promotionRestaurantList.asStateFlow()

    private val _restaurantsByCategory = MutableStateFlow<List<BusinessData>?>(null)
    val restaurantsByCategory = _restaurantsByCategory.asStateFlow()

    private val _filterList = MutableStateFlow<List<String>>(emptyList())
    val filterList = _filterList.asStateFlow()


    private val _restaurantCategoryList = MutableStateFlow<List<CategoryData>>((emptyList()))
    val restaurantCategoryList = _restaurantCategoryList.asStateFlow()

    private val _sponsorRestaurantList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val sponsorRestaurantList = _sponsorRestaurantList.asStateFlow()

    private val _minPrice = MutableStateFlow(0.0)
    private val _maxPrice = MutableStateFlow(0.0)
    private val _minRating = MutableStateFlow(0.0)
    private val _maxRating = MutableStateFlow(0.0)
    private val _minDeliveryTime = MutableStateFlow(0)
    private val _maxDeliveryTime = MutableStateFlow(0)
    private val _minDeliveryFee = MutableStateFlow(0.0)
    private val _maxDeliveryFee = MutableStateFlow(0.0)

    private val _openSheet = MutableStateFlow(true)
    val openSheet = _openSheet.asStateFlow()


    private val _restaurantLoading = MutableStateFlow(true)
    private val _restaurantCategoryLoading = MutableStateFlow(true)
    val loading = _restaurantLoading.asStateFlow().combine(_restaurantCategoryLoading.asStateFlow()) { restaurantLoading, restaurantCategoryLoading ->
        restaurantLoading || restaurantCategoryLoading
    }
    // RestaurantItem
    private val _restaurantById = MutableStateFlow<BusinessData?>(null)
    val restaurantById = _restaurantById.asStateFlow()



    fun getSponsoredRestaurant(){
        val sponsoredRestaurant = _restaurantList.value.filter { it.sponsored && it.isOpen }
        val twoSponsoredRestaurant = sponsoredRestaurant.shuffled(Random(1)).take(2)
        _sponsorRestaurantList.value = twoSponsoredRestaurant
    }

    fun getPopularRestaurant(){
        val popularRestaurantByOrder = _restaurantList.value.filter { it.isOpen }.maxBy { it.orderCount }
        val popularRestaurantByRating = _restaurantList.value.filter { it.isOpen }.maxBy { it.feedback.toDouble() }
        //val sponsoredRestaurant = _restaurantList.value.filter { it.sponsored }.shuffled(Random(1)).take(1)

        _popularRestaurantList.value = listOf(popularRestaurantByOrder,
            popularRestaurantByRating,)

    }

    fun getDiscountedRestaurant() {
        _promotionRestaurantList.value =  _restaurantList.value.filter { it.promotion != "" && it.isOpen }
    }


    suspend fun getSponsors() = dataUseCase.getSponsors().collect {
        when(it){
            is UiEvent.Loading -> {

            }
            is UiEvent.Success -> {
                _sponsors.value = it.data!!.shuffled(Random(1))
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
                _restaurantList.value = it.data!!
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
                _restaurantById.value = it.data!!
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
                _restaurantsByCategory.value = it.data?.ifEmpty { null }
                when{
                    _filterList.value.contains(FilterConstant.OPEN_NOW.title) -> filterOpenNow()
                    _filterList.value.contains(FilterConstant.PROMOTIONS.title) -> filterPromotions()
                    _filterList.value.contains(FilterConstant.PRICE.title) -> filterPrice(_minPrice.value, _maxPrice.value)
                    _filterList.value.contains(FilterConstant.RATING.title) -> filterRating(_minRating.value, _maxRating.value)
                    _filterList.value.contains(FilterConstant.DELIVERY_TIME.title) -> filterDeliveryTime(_maxDeliveryTime.value, _maxDeliveryTime.value)
                    _filterList.value.contains(FilterConstant.DELIVERY_FEE.title) -> filterDeliveryFee(_minDeliveryFee.value, _maxDeliveryFee.value)
                }
            }
            is UiEvent.Error -> {
                _restaurantsByCategory.value = null
            }
        }
    }

    fun setRestaurantsByCategory(filterList: List<BusinessData>) {
        _restaurantsByCategory.value = filterList
    }



    suspend fun getRestaurantCategoryList() = dataUseCase.getRestaurantCategoryList().collect {
        when(it){
            is UiEvent.Loading -> {
                _restaurantCategoryLoading.value = true
            }
            is UiEvent.Success -> {
                _restaurantCategoryList.value = it.data!!
                _restaurantCategoryLoading.value = false
            }
            is UiEvent.Error -> {
                _restaurantCategoryLoading.value = false
            }
        }
    }

    fun closeSheet() {
        _openSheet.value = false
    }
    fun addFilter(filter: String){
        if (_filterList.value.contains(filter)){
            _filterList.value = _filterList.value.filter { it != filter }
            cancelFilter()
            _openSheet.value = false
        }else{
            _filterList.value = _filterList.value + filter
            when(filter){
                FilterConstant.OPEN_NOW.title -> filterOpenNow()
                FilterConstant.PROMOTIONS.title -> filterPromotions()
            }
            _openSheet.value = true
        }
    }

    private fun filterOpenNow(){
        if (_restaurantsByCategory.value != null){
            _restaurantsByCategory.value = _restaurantsByCategory.value!!.filter { it.isOpen }
        }else {
            _restaurantsByCategory.value = _restaurantList.value.filter { it.isOpen }
        }
    }


    private fun filterPromotions(){
        if (_restaurantsByCategory.value != null){
            _restaurantsByCategory.value = _restaurantsByCategory.value!!.filter { it.promotion.isNotEmpty() }
        }else {
            _restaurantsByCategory.value = _restaurantList.value.filter { it.promotion.isNotEmpty() }
        }
    }

    fun filterPrice(min: Double, max: Double){
        _maxPrice.value = max
        _minPrice.value = min
        if (_restaurantsByCategory.value != null){
            _restaurantsByCategory.value = _restaurantsByCategory.value!!.filter { it.minPrice in min..max }
        }else {
            _restaurantsByCategory.value = _restaurantList.value.filter { it.minPrice in min..max }
        }

    }

    fun filterRating(min: Double, max: Double){
        _minRating.value = min
        _maxRating.value = max
        if (_restaurantsByCategory.value != null){
            _restaurantsByCategory.value = _restaurantsByCategory.value!!.filter { it.feedback.toDouble() in min..max }
        }else {
            _restaurantsByCategory.value = _restaurantList.value.filter { it.feedback.toDouble() in min..max }
        }
    }

    fun filterDeliveryTime(min: Int, max: Int){
        _minDeliveryTime.value = min
        _maxDeliveryTime.value = max
        if (_restaurantsByCategory.value != null){
            _restaurantsByCategory.value = _restaurantsByCategory.value!!.filter { it.deliveryTime.toInt() in min..max }
        }else {
            _restaurantsByCategory.value = _restaurantList.value.filter { it.deliveryTime.toInt() in min..max }
        }
    }

    fun filterDeliveryFee(min: Double, max: Double){
        _minDeliveryFee.value = min
        _maxDeliveryFee.value = max
        if (_restaurantsByCategory.value != null){
            _restaurantsByCategory.value = _restaurantsByCategory.value!!.filter { it.deliveryFee.toDouble() in min..max }
        }else {
            _restaurantsByCategory.value = _restaurantList.value.filter { it.deliveryFee.toDouble() in min..max }
        }
    }

    fun clearFilter(){
    _filterList.value = emptyList()
    _restaurantsByCategory.value = null
    }

    private fun cancelFilter(){
        _restaurantsByCategory.value = _restaurantList.value
        when{
            _filterList.value.contains(FilterConstant.OPEN_NOW.title) -> filterOpenNow()
            _filterList.value.contains(FilterConstant.PROMOTIONS.title) -> filterPromotions()
            _filterList.value.contains(FilterConstant.PRICE.title) -> filterPrice(_minPrice.value, _maxPrice.value)
            _filterList.value.contains(FilterConstant.RATING.title) -> filterRating(_minRating.value, _maxRating.value)
            _filterList.value.contains(FilterConstant.DELIVERY_TIME.title) -> filterDeliveryTime(_maxDeliveryTime.value, _maxDeliveryTime.value)
            _filterList.value.contains(FilterConstant.DELIVERY_FEE.title) -> filterDeliveryFee(_minDeliveryFee.value, _maxDeliveryFee.value)
            _filterList.value.isEmpty() -> clearFilter()
        }
    }




}