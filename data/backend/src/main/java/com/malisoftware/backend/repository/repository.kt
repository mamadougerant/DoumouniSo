package com.data.backend.repository

import com.data.backend.TestItemObject
import com.data.backend.TestObjects
import com.malisoftware.backend.remote.DataApi
import com.malisoftware.model.BusinessData
import com.malisoftware.model.BusinessItems
import com.malisoftware.model.CategoryData
import com.malisoftware.model.Sponsors
import com.malisoftware.model.Store
import java.time.LocalDate
import java.time.LocalTime

class Repository : DataApi {
    override fun getSponsors(): List<Sponsors> {
        return TestObjects.sponsors
    }

    override fun getStoreList(): List<Store> {
        return TestObjects.stores
    }

    override fun getRestaurantList(): List<BusinessData> {
        return checkIfBusinessesAreOpen(TestObjects.restaurants)
    }

    override fun getShopList(): List<BusinessData> {
        return checkIfBusinessesAreOpen(TestObjects.shops)
    }

    override fun getRestaurant(id: String): BusinessData {
        return checkIfBusinessIsOpen(TestObjects.restaurants.first { it.id == id })
    }

    override fun getShop(id: String): BusinessData {
        return checkIfBusinessIsOpen(TestObjects.shops.first { it.id == id })
    }

    override fun getRestaurantListByCategory(category: String): List<BusinessData> {
        return checkIfBusinessesAreOpen(TestObjects.restaurants.filter { it.category == category })
    }

    override fun getShopListByCategory(category: String): List<BusinessData> {
        return checkIfBusinessesAreOpen(TestObjects.shops.filter { it.category == category })
    }

    override fun getRestaurantCategoryList(): List<CategoryData> {
        return TestObjects.categories.filter { it.isRestaurant }
    }

    override fun getShopCategoryList(): List<CategoryData> {
        return TestObjects.categories.filter { !it.isRestaurant }
    }

    override fun getRestaurantListBySearch(search: String): List<BusinessData> {
        return checkIfBusinessesAreOpen(TestObjects.restaurants.filter { it.title.contains(search) || it.description.contains(search) })
    }


    private fun checkIfBusinessesAreOpen(business: List<BusinessData>): List<BusinessData> {
        val openBusinesses = mutableListOf<BusinessData>()
        business.forEach {
            openBusinesses.add(checkIfBusinessIsOpen(it))
        }
        return openBusinesses
    }

    private fun checkIfBusinessIsOpen(business: BusinessData): BusinessData {
        val openTime = LocalTime.parse(business.openingTime)
        val closeTime = LocalTime.parse(business.closingTime)
        val currentTime = LocalTime.now()
        val currentDay = LocalDate.now().dayOfWeek.toString().uppercase()
        val openingDays = business.openingDays.map { it.uppercase() }

        return if (currentTime.isAfter(openTime)
            && currentTime.isBefore(closeTime)
            && openingDays.contains(currentDay)){
            calculateRating(business.copy(isOpen = true))
        }else{
            calculateRating(business.copy(isOpen = false))
        }
    }

    private fun calculateRating(business: BusinessData): BusinessData {
        val totalRater = business.raterCount
        val goodRates = business.goodRate

        val rating = (goodRates.toDouble() / totalRater.toDouble()) * 5.0


        return business.copy(feedback = String.format("%.1f", rating))
    }

    // Order
    override fun getRestaurantItems(restaurantId: String): List<BusinessItems> {
        return TestItemObject.restaurantItems.filter { it.businessId == restaurantId }
    }

    override fun getShopItems(shopId: String): List<BusinessItems> {
        return listOf()
    }

}