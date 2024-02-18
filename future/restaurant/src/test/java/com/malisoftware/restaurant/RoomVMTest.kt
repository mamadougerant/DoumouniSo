package com.malisoftware.restaurant

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.malisoftware.backend.TestObjects
import com.malisoftware.backend.dataUseCase.DataUseCase
import com.malisoftware.local.local.BusinessEntity
import com.malisoftware.local.mappers.toItemEntity
import com.malisoftware.local.repository.LocalRepository
import com.malisoftware.model.ItemsList
import com.malisoftware.restaurant.viewModel.RestaurantOrderVM
import com.malisoftware.restaurant.viewModel.RoomViewModel
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import io.realm.kotlin.Realm
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
class RoomVMTest {
/*
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var roomDb : LocalRepository

    @Inject
    lateinit var realm : Realm

    @Inject
    lateinit var dataUseCase: DataUseCase

    private lateinit var viewModel : RoomViewModel

    private lateinit var orderVm : RestaurantOrderVM

    val restaurant = TestObjects.restaurants.random()
    private lateinit var items : List<ItemsList>

    @Before
    fun setUp() {
        hiltRule.inject()
        viewModel = RoomViewModel(roomDb)
        orderVm = RestaurantOrderVM(dataUseCase)
        runBlocking {
            orderVm.getRestaurantItems(restaurant.id)
            items = orderVm.state.value.restaurantItems
        }
    }


    @Test
    fun ` test insert order items`() = runBlocking {
        if (items.isEmpty()) return@runBlocking
        viewModel.insertOrder(restaurant,restaurant.id)
        viewModel.insertOrderItem(items[0].items[0].toItemEntity(restaurant.id))

        val orders = viewModel.orders.value
        assert(orders.isNotEmpty()) { "Orders are empty" }

        val roomItems = viewModel.items.value
        assert(items.isNotEmpty()) { "Items are empty" }
        assert(roomItems[0] == items[0].items[0].toItemEntity(restaurant.id)) { "Restaurant id is not same" }
    }
*/
}