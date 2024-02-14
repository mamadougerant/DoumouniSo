package com.malisoftware.orders

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.local.local.ItemOrderEntity
import com.malisoftware.local.reamlModel.RealmItemOrder
import com.malisoftware.local.repository.LocalRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OrderViewModel @Inject constructor(
    private val roomDb: LocalRepository
) : ViewModel(){

    private val _orderedItem = MutableStateFlow<List<RealmItemOrder>>(emptyList())
    val orderedItem = _orderedItem.asStateFlow()


    suspend fun getCompletedOrder() = roomDb.getAllCompletedOrder().collect{
        when(it){
            is UiEvent.Loading -> {
                //Do something
            }
            is UiEvent.Success -> {
                it.data?.collectLatest { value ->
                    _orderedItem.value = value
                }
            }
            is UiEvent.Error -> {
                //Do something
            }
        }
    }
}