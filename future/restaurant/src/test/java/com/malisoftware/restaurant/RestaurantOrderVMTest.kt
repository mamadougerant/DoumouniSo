package com.malisoftware.restaurant

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.malisoftware.backend.TestObjects
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.restaurant.viewModel.RestaurantOrderVM
import com.malisoftware.restaurant.viewModel.RestaurantViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject


@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class RestaurantOrderVMTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dataUseCase: DataUseCase

    private lateinit var viewModel : RestaurantOrderVM

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = RestaurantOrderVM(dataUseCase)
    }

    @Test
    fun `test Data restaurant items by restaurantId`() = runBlocking {
        val restaurant = TestObjects.restaurants.random()
        viewModel.getRestaurantItems(restaurant.id)
        val vmState = viewModel.state.value

        assert(vmState.restaurantItems.isNotEmpty()) { "${restaurant.title} items are empty" }
        assert(vmState.itemsInPromotion.isNotEmpty()) { "${restaurant.title}  items are empty" }
    }

    @Test
    fun `test Data restaurant by Ids`() = runBlocking {
        val restaurantIds = TestObjects.restaurants.map { it.id }
        viewModel.getRestaurantsItems(restaurantIds)
        val vmState = viewModel.restaurantsItems.value

        assert(vmState.isNotEmpty()) { "Restaurants items are empty" }
    }

    @Test
    fun `test search restaurent items`() = runBlocking {
        val restaurant = TestObjects.restaurants.random()
        viewModel.getRestaurantItems(restaurant.id)
        val vmState = viewModel.state.value

        assert(vmState.restaurantItems.isNotEmpty()) { "${restaurant.title} items are empty" }
        assert(vmState.itemsInPromotion.isNotEmpty()) { "${restaurant.title}  items are empty" }

        val items = vmState.restaurantItems
        val search = items.random().title

        viewModel.searchItems(search)
        val searchItems = viewModel.state.value.restaurantItems

        assert(searchItems.isNotEmpty()) { "Search items are empty" }
    }
}