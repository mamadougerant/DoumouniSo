package com.malisoftware.backend.remote

import com.malisoftware.model.BusinessData
import com.malisoftware.model.BusinessItems
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Sponsors
import com.malisoftware.model.Store

interface DataApi {
    fun getSponsors(): List<Sponsors>
    fun getStoreList(): List<Store>
    fun getRestaurantList(): List<BusinessData>
    fun getShopList(): List<BusinessData>
    fun getRestaurant(id: String): BusinessData
    fun getShop(id: String): BusinessData
    fun getRestaurantListByCategory(category: String): List<BusinessData>
    fun getShopListByCategory(category: String): List<BusinessData>
    fun getRestaurantCategoryList(): List<CategoryData>
    fun getShopCategoryList(): List<CategoryData>
    fun getRestaurantListBySearch(search: String): List<BusinessData>
    fun getShopListBySearch(search: String): List<BusinessData>

    // Order
    fun getRestaurantItems(restaurantId: String): List<BusinessItems>

    fun getShopItems(shopId: String): List<BusinessItems>

}

