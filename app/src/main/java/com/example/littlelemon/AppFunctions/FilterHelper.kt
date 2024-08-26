package com.example.littlelemon.AppFunctions

import com.example.littlelemon.Database.MenuJsonList
import com.example.littlelemon.Database.ProductItem

sealed class FilterType {
    object All : FilterType()
    object Drinks : FilterType()
    object Mains : FilterType()
    object Sides : FilterType()
    object Dessert : FilterType()
}

fun filterProducts(type: String, productsList: List<MenuJsonList.MenuItemsJson>): List<MenuJsonList.MenuItemsJson> {
    return when (type) {
        "All" -> productsList
        "Dessert" -> productsList.filter { it.category == "Dessert" }
        "Drinks" -> productsList.filter { it.category == "Drinks" }
        "Mains" -> productsList.filter { it.category == "Food" }
        "Side" -> productsList.filter { it.category == "Side" }
        else -> {productsList}
    }
}