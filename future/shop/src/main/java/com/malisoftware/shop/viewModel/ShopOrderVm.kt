package com.malisoftware.shop.viewModel

import androidx.lifecycle.ViewModel
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.model.BusinessItems
import com.malisoftware.model.Items
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class ShopOrderVm @Inject constructor(
    private val dataUseCase: DataUseCase
): ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _shopItems = MutableStateFlow(emptyList<BusinessItems>())
    val shopItems = _shopItems.asStateFlow()

    private val _itemsInPromotion = MutableStateFlow(emptyList<Items>())
    val itemsInPromotion = _itemsInPromotion.asStateFlow()

    private val _shopsItems = MutableStateFlow(emptyList<BusinessItems>())
    val shopsItems = _shopsItems.asStateFlow()

    private val _shopFilterContent = MutableStateFlow<Pair<String,List<Items>>?>(null)
    val shopFilterContent = _shopFilterContent.asStateFlow()


    suspend fun getShopItems(shopId: String) {
        dataUseCase.getShopItems(shopId).collect {
            when (it) {
                is UiEvent.Loading -> {
                    _loading.value = true
                }
                is UiEvent.Success -> {
                    _shopItems.value = it.data!!
                    val items = it.data!!.map { it.items }.flatten().map { it.items }.flatten().shuffled()
                    _itemsInPromotion.value = items.filter { it.promotion != "" }
                    _loading.value = false
                }
                is UiEvent.Error -> {
                    _loading.value = false
                }
            }
        }
    }

    suspend fun getShopsItems(shopId: List<String>){
        val items = MutableStateFlow(emptyList<BusinessItems>())
        shopId.forEach {
            dataUseCase.getShopItems(it).collect {
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
        _shopsItems.value = items.value
    }

    fun setFilterContent(title: String = "", items: List<Items>?){
        if (items.isNullOrEmpty()) _shopFilterContent.value = null
        else _shopFilterContent.value = title to items
    }

}