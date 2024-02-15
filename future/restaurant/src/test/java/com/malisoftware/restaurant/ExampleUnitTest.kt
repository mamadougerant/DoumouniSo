package com.malisoftware.restaurant

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.components.constants.FilterConstant
import com.malisoftware.model.BusinessData
import com.malisoftware.restaurant.viewModel.RestaurantViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import javax.inject.Inject


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
class RestaurantViewModelTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var dataUseCase: DataUseCase

    private lateinit var viewModel : RestaurantViewModel

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = RestaurantViewModel(dataUseCase)
    }



    @Test
    fun `test Data retrieving`() = runBlocking {
        viewModel.fetchAllData()
        delay(1000)
        val vmState = viewModel.vmState.value

        assert(vmState.sponsors.isNotEmpty())
        assert(vmState.restaurantList.isNotEmpty())
        assert(vmState.restaurantCategoryList.isNotEmpty())
        assert(vmState.popularRestaurantList.isNotEmpty())
        assert(vmState.promotionRestaurantList.isNotEmpty())
        assert(vmState.sponsorRestaurantList.isNotEmpty())
    }
    @Test
    fun `test get restaurant by id`() = runBlocking {
        val id = "Burger house"
        viewModel.getRestaurant(id)
        val vmState = viewModel.vmState.value
        assert(vmState.restaurantById != null)
        assert(vmState.restaurantById!!.id == id)
    }

    @Test
    fun `test get non existing restaurant by id`() = runBlocking {
        val id = "non existing restaurant"
        viewModel.getRestaurant(id)

        val vmState = viewModel.vmState.value
        assert(vmState.restaurantById == null)
    }

    @Test
    fun `test get restaurant by category`() = runBlocking {
        val category = "Burger"
        viewModel.getRestaurantListByCategory(category)

        val vmState = viewModel.vmState.value
        assert(vmState.restaurantsByCategory != null)
        assert(vmState.restaurantsByCategory!!.isNotEmpty())
        assert(vmState.restaurantsByCategory!!.all { it.category == category })
    }

    @Test
    fun `test get non existing restaurant by category`() = runBlocking {
        val category = "non existing category"
        viewModel.getRestaurantListByCategory(category)
        delay(1000)
        val vmState = viewModel.vmState.value
        assert(vmState.restaurantsByCategory == null)
    }

    @Test
    fun `test set empty Restaurants By Category`(): Unit = runBlocking {
        val vmState = viewModel.vmState.value

        vmState.restaurantsByCategory?.let { assert(false) }
    }

    @Test
    fun `test set Restaurants By Category`(): Unit = runBlocking {
        val vmState = viewModel.vmState.value

        val filter = listOf(
            BusinessData().copy(title = "Burger house"),
            BusinessData().copy(title = "pizza house"),
        )
        viewModel.setRestaurantsByCategory(filter)

        vmState.restaurantsByCategory?.let { assert(it.isNotEmpty()) }
        vmState.restaurantsByCategory?.let { assert(it.size == filter.size) }
    }

    @Test
    fun `addFilter test`() = runBlocking {
        viewModel.addFilter("Burger")
        val vmState = viewModel.vmState.value

        assert(vmState.filterList.isNotEmpty()) { "size: ${vmState.filterList.size}" }
        assert(vmState.filterList.contains("Burger")) { "filterList1: ${vmState.filterList}" }
        assert(vmState.openSheet) { "openSheet1: ${vmState.openSheet}" }

        viewModel.addFilter("Pizza")
        val vmState2 = viewModel.vmState.value

        assert(vmState2.filterList.isNotEmpty()) { "filterList2: ${vmState.filterList}" }
        assert(vmState2.filterList.size == 2) { "size: ${vmState.filterList}" }
        assert(vmState2.openSheet) { "openSheet2: ${vmState.openSheet}" }

        viewModel.addFilter("Pizza")
        val vmState3 = viewModel.vmState.value

        assert(vmState3.filterList.size == 1) { "size: ${vmState.filterList}" }
        assert(vmState3.filterList.contains("Burger")) { "filterList3: ${vmState.filterList}" }
        assert(!vmState3.openSheet) { "openSheet3: ${vmState.openSheet}" }

    }

    @Test
    fun `removeFilter test`() = runBlocking {
        viewModel.addFilter("Burger")
        viewModel.addFilter("Pizza")
        viewModel.removeFilter("Burger")
        val vmState = viewModel.vmState.value

        assert(vmState.filterList.size == 1) { "size: ${vmState.filterList}" }
        assert(vmState.filterList.contains("Pizza")) { "filterList1: ${vmState.filterList}" }
        assert(!vmState.openSheet) { "openSheet1: ${vmState.openSheet}" }

        viewModel.removeFilter("Pizza")
        val vmState2 = viewModel.vmState.value

        assert(vmState2.filterList.isEmpty()) { "filterList2: ${vmState.filterList}" }
        assert(!vmState2.openSheet) { "openSheet2: ${vmState.openSheet}" }

        viewModel.removeFilter("Pizza")
        val vmState3 = viewModel.vmState.value

        assert(vmState3.filterList.isEmpty()) { "filterList3: ${vmState.filterList}" }
        assert(!vmState3.openSheet) { "openSheet3: ${vmState.openSheet}" }

    }

    @Test
    fun ` filter result test`(){
        val openFilter = FilterConstant.OPEN_NOW.title
        val promotionFilter = FilterConstant.PROMOTIONS.title

        viewModel.addFilter(openFilter)
        val vmState = viewModel.vmState.value

        vmState.restaurantsByCategory?.let { assert(it.all { it.isOpen }) { "filterList1: ${vmState.filterList}" } }

        // remove open now filter
        viewModel.addFilter(openFilter)
        viewModel.addFilter(promotionFilter)
        val vmState2 = viewModel.vmState.value

        vmState2.restaurantsByCategory?.let { assert(it.all { it.promotion != "" }) { "filterList2: ${vmState.filterList}" } }

        // open now and promotion filter
        viewModel.removeFilter(openFilter)
        val vmState3 = viewModel.vmState.value
        vmState3.restaurantsByCategory?.let { assert(it.all { it.isOpen && it.promotion != "" }) { "filterList3: ${vmState.filterList}" } }

        // open now - promotion filter - min price filter

        viewModel.addFilter(FilterConstant.PRICE.title)
        viewModel.filterPrice(1000.0, 2500.0)
        val vmState4 = viewModel.vmState.value

        vmState4.restaurantsByCategory?.let { assert(it.all {
            it.minPrice in 1000.0..2500.0 && it.isOpen && it.promotion != ""
        }) { "filterList4: ${vmState.filterList}" } }

        // open now - promotion filter - min price filter - delivery time filter
        viewModel.addFilter(FilterConstant.DELIVERY_TIME.title)
        viewModel.filterDeliveryTime(10, 30)
        val vmState5 = viewModel.vmState.value

        vmState5.restaurantsByCategory?.let { assert(it.all {
            it.minPrice in 1000.0..2500.0 && it.isOpen &&
                    it.promotion != "" && it.deliveryTime in 10..30
        }) { "filterList5: ${vmState.filterList}" } }

        // open now - promotion filter - min price filter - delivery time filter - rating filter

        viewModel.addFilter(FilterConstant.RATING.title)
        viewModel.filterRating(4.0, 5.0)
        val vmState6 = viewModel.vmState.value

        vmState6.restaurantsByCategory?.let { assert(it.all {
            it.minPrice in 1000.0..2500.0 && it.isOpen &&
                    it.promotion != "" && it.deliveryTime in 10..30 && it.feedback.toDouble() in 4.0..5.0
        }) { "filterList6: ${vmState.filterList}" } }

        // open now - promotion filter - min price filter - delivery time filter - rating filter - delivery fee filter

        viewModel.addFilter(FilterConstant.DELIVERY_FEE.title)
        viewModel.filterDeliveryFee(0.0, 1000.0)
        val vmState7 = viewModel.vmState.value

        vmState7.restaurantsByCategory?.let { assert(it.all {
            it.minPrice in 1000.0..2500.0 && it.isOpen &&
                    it.promotion != "" && it.deliveryTime in 10..30 &&
                    it.feedback.toDouble() in 4.0..5.0 &&
                    it.deliveryFee in 0.0..1000.0
        }) { "filterList7: ${vmState.filterList}" } }

        // cleaar all filters
        viewModel.clearFilter()
        val vmState8 = viewModel.vmState.value

        assert(vmState8.filterList.isEmpty()) { "filterList8: ${vmState.filterList}" }
    }





}