package com.malisoftware.backend.dataUseCase

import com.malisoftware.components.uiEvent.UiEvent
import com.malisoftware.backend.remote.DataApi
import com.malisoftware.model.BusinessItems
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class DataUseCase(
    private val dataApi: DataApi
) {
    private val ioDispatcher = Dispatchers.IO

    fun getSponsors() = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getSponsors()
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getStoreList() = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getStoreList()
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getRestaurantList() = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getRestaurantList()
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getShopList() = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getShopList()
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getRestaurant(id: String) = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getRestaurant(id)
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getShop(id: String) = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getShop(id)
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getRestaurantListByCategory(category: String) = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getRestaurantListByCategory(category)
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getShopListByCategory(category: String) = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getShopListByCategory(category)
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getRestaurantCategoryList() = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getRestaurantCategoryList()
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getShopCategoryList() = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getShopCategoryList()
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getRestaurantListBySearch(search: String) = flow {
        emit(UiEvent.Loading())
        delay(500)
        val result = dataApi.getRestaurantListBySearch(search)
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)

    fun getShopListBySearch(search: String) = flow {
        emit(UiEvent.Loading())
        delay(500)
        val result = dataApi.getShopListBySearch(search)
        emit(UiEvent.Success(result))
    }.catch { emit(UiEvent.Error(message = it.message.toString())) }.flowOn(ioDispatcher)

    fun getRestaurantItems(restaurantId: String) = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getRestaurantItems(restaurantId)
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)


    // Shop
    fun getShopItems(shopId: String) = flow {
        emit(UiEvent.Loading())
        val result = dataApi.getShopItems(shopId)
        emit(UiEvent.Success(result))
    }.catch {
        emit(UiEvent.Error(message = it.message.toString()))
    }.flowOn(ioDispatcher)


}