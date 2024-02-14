package com.malisoftware.restaurant.viewModel

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
    }


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
                _restaurantsByCategory.value = it.data?.ifEmpty { null }
                when{
                    _vmState.value.filterList.contains(FilterConstant.OPEN_NOW.title) -> filterOpenNow()
                    _vmState.value.filterList.contains(FilterConstant.PROMOTIONS.title) -> filterPromotions()
                    _vmState.value.filterList.contains(FilterConstant.PRICE.title) -> filterPrice(_minPrice.value, _maxPrice.value)
                    _vmState.value.filterList.contains(FilterConstant.RATING.title) -> filterRating(_minRating.value, _maxRating.value)
                    _vmState.value.filterList.contains(FilterConstant.DELIVERY_TIME.title) -> filterDeliveryTime(_maxDeliveryTime.value, _maxDeliveryTime.value)
                    _vmState.value.filterList.contains(FilterConstant.DELIVERY_FEE.title) -> filterDeliveryFee(_minDeliveryFee.value, _maxDeliveryFee.value)
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
        _vmState.update { state -> state.copy(filterList = emptyList()) }
        _restaurantsByCategory.value = null
    }

    private fun cancelFilter(){
        _restaurantsByCategory.value = _restaurantList.value
        when{
            _vmState.value.filterList.contains(FilterConstant.OPEN_NOW.title) -> filterOpenNow()
            _vmState.value.filterList.contains(FilterConstant.PROMOTIONS.title) -> filterPromotions()
            _vmState.value.filterList.contains(FilterConstant.PRICE.title) -> filterPrice(_minPrice.value, _maxPrice.value)
            _vmState.value.filterList.contains(FilterConstant.RATING.title) -> filterRating(_minRating.value, _maxRating.value)
            _vmState.value.filterList.contains(FilterConstant.DELIVERY_TIME.title) -> filterDeliveryTime(_maxDeliveryTime.value, _maxDeliveryTime.value)
            _vmState.value.filterList.contains(FilterConstant.DELIVERY_FEE.title) -> filterDeliveryFee(_minDeliveryFee.value, _maxDeliveryFee.value)
            _vmState.value.filterList.isEmpty() -> clearFilter()
        }
    }




}