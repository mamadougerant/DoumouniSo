package com.future.shop.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.malisoftware.components.constants.FilterConstant
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.model.BusinessData
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Sponsors
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class ShopViewModel @Inject constructor(
    private val dataUseCase: DataUseCase
): ViewModel() {

    private val _sponsors = MutableStateFlow<List<Sponsors>>(emptyList())
    val sponsors = _sponsors.asStateFlow()

    private val _shopList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val shopList = _shopList.asStateFlow()

    private val _shopCategoryList = MutableStateFlow<List<CategoryData>>((emptyList()))
    val shopCategoryList = _shopCategoryList.asStateFlow()

    private val _sponsorShopList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val sponsorShopList = _sponsorShopList.asStateFlow()

    private val _shopById = MutableStateFlow<BusinessData?>(null)
    val shopById = _shopById.asStateFlow()

    private val _shopByCategory = MutableStateFlow<List<BusinessData>?>(null)
    val shopByCategory = _shopByCategory.asStateFlow()

    private val _filterList = MutableStateFlow<List<String>>(emptyList())
    val filterList = _filterList.asStateFlow()

    private val _shopInPromotions = MutableStateFlow<List<BusinessData>>(emptyList())
    val shopInPromotions = _shopInPromotions.asStateFlow()


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

    private val _shopLoading = MutableStateFlow(true)
    private val _shopCategoryLoading = MutableStateFlow(true)
    val loading = _shopLoading.asStateFlow().combine(_shopCategoryLoading.asStateFlow()) { shopLoading, shopCategoryLoading ->
        shopLoading || shopCategoryLoading
    }



    fun getSponsoredShop(){
        val sponsoredShop = _shopList.value.filter { it.sponsored && it.isOpen }
        val twoSponsoredShop = sponsoredShop.shuffled(Random(1)).take(3)
        _sponsorShopList.value = twoSponsoredShop
    }

    fun getShopInPromotion(){
        val discountedShop = _shopList.value.filter { it.promotion.isNotEmpty() && it.isOpen }
        _shopInPromotions.value = discountedShop
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


    suspend fun getShopList() = dataUseCase.getShopList().collect {
        when(it){
            is UiEvent.Loading -> {
                _shopLoading.value = true
            }
            is UiEvent.Success -> {
                _shopList.value = it.data!!
                _shopLoading.value = false
            }
            is UiEvent.Error -> {
                _shopLoading.value = false
            }
        }
    }


    suspend fun getShopById(id: String) = dataUseCase.getShop(id).collect {

        when(it){
            is UiEvent.Loading -> {
                _shopLoading.value = true
            }
            is UiEvent.Success -> {
                _shopById.value = it.data!!
                _shopLoading.value = false
            }
            is UiEvent.Error -> {
                _shopLoading.value = false
            }
        }
    }



    suspend fun getShopListByCategory(category: String) = dataUseCase.getShopListByCategory(category).collect {
        when(it){
            is UiEvent.Loading -> {
                Log.d("MainHome", "getShopListByCategory: Loading")
                //TODO
            }
            is UiEvent.Success -> {
                _shopByCategory.value = it.data!!.ifEmpty { null }
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
                Log.d("MainHome", "getShopListByCategory: Error")
                //TODO
            }
        }
    }

    fun setShopByCategory(shopList: List<BusinessData>){
        _shopByCategory.value = shopList
    }



    suspend fun getShopCategoryList() = dataUseCase.getShopCategoryList().collect {
        when(it){
            is UiEvent.Loading -> {
                _shopCategoryLoading.value = true
            }
            is UiEvent.Success -> {
                _shopCategoryList.value = it.data!!
                _shopCategoryLoading.value = false
            }
            is UiEvent.Error -> {
                _shopCategoryLoading.value = false
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
        if (_shopByCategory.value != null){
            _shopByCategory.value = _shopByCategory.value!!.filter { it.isOpen }
        }else {
            _shopByCategory.value = _shopList.value.filter { it.isOpen }
        }
    }


    private fun filterPromotions(){
        if (_shopByCategory.value != null){
            _shopByCategory.value = _shopByCategory.value!!.filter { it.promotion.isNotEmpty() }
        }else {
            _shopByCategory.value = _shopList.value.filter { it.promotion.isNotEmpty() }
        }
    }

    fun filterPrice(min: Double, max: Double){
        _maxPrice.value = max
        _minPrice.value = min
        if (_shopByCategory.value != null){
            _shopByCategory.value = _shopByCategory.value!!.filter { it.minPrice in min..max }
        }else {
            _shopByCategory.value = _shopList.value.filter { it.minPrice in min..max }
        }

    }

    fun filterRating(min: Double, max: Double){
        _minRating.value = min
        _maxRating.value = max
        if (_shopByCategory.value != null){
            _shopByCategory.value = _shopByCategory.value!!.filter { it.feedback.toDouble() in min..max }
        }else {
            _shopByCategory.value = _shopList.value.filter { it.feedback.toDouble() in min..max }
        }
    }

    fun filterDeliveryTime(min: Int, max: Int){
        _minDeliveryTime.value = min
        _maxDeliveryTime.value = max
        if (_shopByCategory.value != null){
            _shopByCategory.value = _shopByCategory.value!!.filter { it.deliveryTime.toInt() in min..max }
        }else {
            _shopByCategory.value = _shopList.value.filter { it.deliveryTime.toInt() in min..max }
        }
    }

    fun filterDeliveryFee(min: Double, max: Double){
        _minDeliveryFee.value = min
        _maxDeliveryFee.value = max
        if (_shopByCategory.value != null){
            _shopByCategory.value = _shopByCategory.value!!.filter { it.deliveryFee.toDouble() in min..max }
        }else {
            _shopByCategory.value = _shopList.value.filter { it.deliveryFee.toDouble() in min..max }
        }
    }

    fun clearFilter(){
        _filterList.value = emptyList()
        _shopByCategory.value = null
    }

    private fun cancelFilter(){
        _shopByCategory.value = _shopList.value
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