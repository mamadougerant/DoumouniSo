package com.malisoftware.search

import android.util.Log
import androidx.lifecycle.ViewModel
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.local.repository.LocalRepository
import com.malisoftware.model.BusinessData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val data : DataUseCase,
    private val roomData: LocalRepository
) : ViewModel() {

    private val _restaurantSearchResult = MutableStateFlow<List<BusinessData>>(emptyList())
    val restaurantSearchResult = _restaurantSearchResult

    private val _shopSearchResult = MutableStateFlow<List<BusinessData>>(emptyList())
    val shopSearchResult = _shopSearchResult

    private val _restaurantRecentSearch = MutableStateFlow<List<String>>(emptyList())
    val restaurantRecentSearch = _restaurantRecentSearch.asStateFlow()

    private val _shopRecentSearch = MutableStateFlow<List<String>>(emptyList())
    val shopRecentSearch = _shopRecentSearch.asStateFlow()

    private val _restaurantSearchQuery = MutableStateFlow<List<String>>(emptyList())
    val restaurantSearchQuery = _restaurantSearchQuery.asStateFlow()

    private val _shopSearchQuery = MutableStateFlow<List<String>>(emptyList())
    val shopSearchQuery = _shopSearchQuery.asStateFlow()


    suspend fun search(query: String) = data.getRestaurantListBySearch(query).collect{
        when (it) {
            is UiEvent.Error -> {}
            is UiEvent.Loading -> {}
            is UiEvent.Success -> {
                if (query.isEmpty() || query.isBlank()) {
                    _restaurantSearchResult.value = emptyList()
                } else {
                    _restaurantSearchResult.value = it.data!!.filter { it.isRestaurant }.take(10)
                }
            }
        }
    }

    suspend fun searchShop(query: String) = data.getShopListBySearch(query).collect{
        when (it) {
            is UiEvent.Error -> {}
            is UiEvent.Loading -> {}
            is UiEvent.Success -> {
                if (query.isEmpty() || query.isBlank()) {
                    _shopSearchResult.value = emptyList()
                } else {
                    _shopSearchResult.value = it.data!!.filter { !it.isRestaurant }.take(10)
                }
            }
        }
    }

    suspend fun getRecentSearch() = roomData.getSearchQuery().collect{
        when (it) {
            is UiEvent.Error -> {}
            is UiEvent.Loading -> {}
            is UiEvent.Success -> {
                it.data!!.collectLatest {
                    _restaurantRecentSearch.value = it.filter { it.isRestaurant }.map { it.query }
                    _shopRecentSearch.value = it.filter { !it.isRestaurant }.map { it.query }
                }
            }
        }
    }

    suspend fun getRestaurantCategoryList() = data.getRestaurantCategoryList().collect{
        when (it) {
            is UiEvent.Error -> {}
            is UiEvent.Loading -> {}
            is UiEvent.Success -> {
                _restaurantSearchQuery.value = it.data!!.map { it.title }
            }
        }
    }

    suspend fun getShopCategoryList() = data.getShopCategoryList().collect{
        when (it) {
            is UiEvent.Error -> {}
            is UiEvent.Loading -> {}
            is UiEvent.Success -> {
                _shopSearchQuery.value = it.data!!.map { it.title }
            }
        }
    }

    fun saveSearchQuery(title: String) = roomData.insertSearchQuery(title)


}