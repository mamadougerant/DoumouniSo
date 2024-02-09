package com.malisoftware.home.viewModel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.model.BusinessData
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Sponsors
import com.malisoftware.model.Store
import com.malisoftware.components.uiEvent.UiEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import java.util.Random
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val dataUseCase: DataUseCase
) : ViewModel() {

    private val _sponsors = MutableStateFlow<List<Sponsors>>(emptyList())
    val sponsors = _sponsors.asStateFlow()

    private val _storeList = MutableStateFlow<List<Store>>((emptyList()))
    val storeList = _storeList.asStateFlow()

    private val _restaurantList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val restaurantList = _restaurantList.asStateFlow()

    private val _shopList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val shopList = _shopList.asStateFlow()

    private val _restaurantCategoryList = MutableStateFlow<List<CategoryData>>((emptyList()))
    val restaurantCategoryList = _restaurantCategoryList.asStateFlow()

    private val _shopCategoryList = MutableStateFlow<List<CategoryData>>((emptyList()))
    val shopCategoryList = _shopCategoryList.asStateFlow()

    private val _sponsorRestaurantList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val sponsorRestaurantList = _sponsorRestaurantList.asStateFlow()

    private val _sponsorShopList = MutableStateFlow<List<BusinessData>>((emptyList()))
    val sponsorShopList = _sponsorShopList.asStateFlow()

    private val _restaurantLoading = MutableStateFlow(true)
    private val _restaurantCategoryLoading = MutableStateFlow(true)
    private val _storeLoading = MutableStateFlow(true)
    val loading = _restaurantLoading.asStateFlow().combine(_restaurantCategoryLoading.asStateFlow()) { restaurantLoading, restaurantCategoryLoading ->
        restaurantLoading || restaurantCategoryLoading
    }.combine(_storeLoading.asStateFlow()) { storeLoading, restaurantCategoryLoading ->
        storeLoading || restaurantCategoryLoading
    }



    fun getSponsoredRestaurant(){
        val sponsoredRestaurant = _restaurantList.value.filter { it.sponsored && it.isOpen }
        val twoSponsoredRestaurant = sponsoredRestaurant.shuffled(Random(1)).take(2)
        _sponsorRestaurantList.value = twoSponsoredRestaurant
    }

    fun getSponsoredShop(){
        val sponsoredShop = _shopList.value.filter { it.sponsored && it.isOpen }
        val twoSponsoredShop = sponsoredShop.shuffled(Random(1)).take(2)
        _sponsorShopList.value = twoSponsoredShop
    }



    suspend fun getSponsors() = dataUseCase.getSponsors().collect {
        when(it){
            is UiEvent.Loading -> {
                _storeLoading.value = true
            }
            is UiEvent.Success -> {
                _sponsors.value = it.data!!.shuffled()
                _storeLoading.value = false
            }
            is UiEvent.Error -> {
                _storeLoading.value = false
            }
        }
    }

    suspend fun getStoreList() = dataUseCase.getStoreList().collect {
        when(it){
            is UiEvent.Loading -> {
                _restaurantCategoryLoading.value = true
            }
            is UiEvent.Success -> {
                _storeList.value = it.data!!
                _restaurantCategoryLoading.value = false
            }
            is UiEvent.Error -> {
                _restaurantCategoryLoading.value = false
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

    suspend fun getShopList() = dataUseCase.getShopList().collect {
        when(it){
            is UiEvent.Loading -> {
                Log.d("MainHome2", "getShopList: Loading")
                //TODO
            }
            is UiEvent.Success -> {
                _shopList.value = it.data!!
                Log.d("MainHome2", "getShopList: ${it.data}")
            }
            is UiEvent.Error -> {
                Log.d("MainHome2", "getShopList: Error")
                //TODO
            }
        }
    }

    suspend fun getRestaurant(id: String) = dataUseCase.getRestaurant(id).collect {

    }

    suspend fun getShop(id: String) = dataUseCase.getShop(id).collect {

    }

    suspend fun getRestaurantListByCategory(category: String) = dataUseCase.getRestaurantListByCategory(category).collect {

    }

    suspend fun getShopListByCategory(category: String) = dataUseCase.getShopListByCategory(category).collect {

    }

    suspend fun getRestaurantCategoryList() = dataUseCase.getRestaurantCategoryList().collect {
        when(it){
            is UiEvent.Loading -> {
                Log.d("MainHome", "getRestaurantCategoryList: Loading")
                //TODO
            }
            is UiEvent.Success -> {
                _restaurantCategoryList.value = it.data!!
            }
            is UiEvent.Error -> {
                Log.d("MainHome", "getRestaurantCategoryList: Error")
                //TODO
            }
        }
    }

    suspend fun getShopCategoryList() = dataUseCase.getShopCategoryList().collect {
        when(it){
            is UiEvent.Loading -> {
                Log.d("MainHome", "getShopCategoryList: Loading")
                //TODO
            }
            is UiEvent.Success -> {
                _shopCategoryList.value = it.data!!
            }
            is UiEvent.Error -> {
                Log.d("MainHome", "getShopCategoryList: Error")
                //TODO
            }
        }
    }

    suspend fun getRestaurantListBySearch(search: String) = dataUseCase.getRestaurantListBySearch(search).collect {

    }

}