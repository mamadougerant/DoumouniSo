package com.malisoftware.ai.model.request


import kotlinx.serialization.Serializable

@Serializable
data class Properties(
    //@SerialName("location")
   // val location: Location,
    //@SerialName("unit")
    //val unit: Unit
    val restaurantName: RestaurantName,
    val foodName: FoodName,
    val foodCategory: FoodCategory,
    val foodQuantity: FoodQuantity,
    val mealId: MealId
)

@Serializable
data class MealId(
    val type: String = "String",
    val description: String
)
@Serializable
data class RestaurantName(
    val type: String = "String",
    val description: String
)
@Serializable
data class FoodName(
    val type: String = "String",
    val description: String
)
@Serializable
data class FoodCategory(
    val type: String = "String",
    val description: String
)
@Serializable
data class FoodQuantity(
    val type: String = "String",
    val description: String
)
