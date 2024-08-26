package com.example.littlelemon.Database

import com.example.littlelemon.R

val Categories = listOf(
    "All",
    "Mains",
    "Side",
    "Dessert",
    "Drinks",
)

data class BasketList(
    val id: Int,
    val amount: Int,
)

data class BasketOrderList(
    val id: Int,
    val amount: Int,
    val name: String,
    val price: Int
)

fun getImageResourceById(id: Int): Int {
    return when (id) {
        1 -> R.drawable.black_tea
        2 -> R.drawable.green_tea
        3 -> R.drawable.espresso
        4 -> R.drawable.cappuccino
        5 -> R.drawable.latte
        6 -> R.drawable.mocha
        7 -> R.drawable.boeuf_bourguignon
        8 -> R.drawable.bouillabaisse
        9 -> R.drawable.lasagna
        10 -> R.drawable.onion_soup
        11 -> R.drawable.salmon_en_papillote
        12 -> R.drawable.quiche_lorraine
        13 -> R.drawable.custard_tart
        14 -> R.drawable.croissant
        15 -> R.drawable.greeksalad
        16 -> R.drawable.bruschetta
        17 -> R.drawable.lemondessert
        else -> R.drawable.greeksalad  // Replace with a default image if needed
    }
}

data class ProductItem(
    val name: String,
    val price: Int,
    val category: String,
    val description: String,
    val image: Int
)

object ProductWarehouse {
    val productsList = mutableListOf(
        ProductItem(
            "Black tea",
            3,
            "Drinks",
            "A strong, aromatic black tea.",
            R.drawable.black_tea
        ),
        ProductItem(
            "Green tea",
            3,
            "Drinks",
            "A refreshing green tea with a light taste.",
            R.drawable.green_tea
        ),
        ProductItem(
            "Espresso",
            5,
            "Drinks",
            "A strong and bold espresso shot.",
            R.drawable.espresso
        ),
        ProductItem(
            "Cappuccino",
            8,
            "Drinks",
            "Creamy cappuccino with steamed milk.",
            R.drawable.cappuccino
        ),
        ProductItem(
            "Latte",
            8,
            "Drinks",
            "Smooth latte with a hint of sweetness.",
            R.drawable.latte
        ),
        ProductItem(
            "Mocha",
            10,
            "Drinks",
            "Chocolatey mocha with espresso and milk.", R.drawable.mocha
        ),
        ProductItem(
            "Boeuf bourguignon",
            15,
            "Food",
            "A rich, hearty beef stew with red wine.", R.drawable.boeuf_bourguignon
        ),
        ProductItem(
            "Bouillabaisse",
            20,
            "Food",
            "A classic French seafood stew.", R.drawable.bouillabaisse
        ),
        ProductItem(
            "Lasagna",
            15,
            "Food",
            "Layered pasta with meat, cheese, and sauce.", R.drawable.lasagna
        ),
        ProductItem(
            "Onion soup",
            12,
            "Food",
            "Savory soup topped with melted cheese.", R.drawable.onion_soup
        ),
        ProductItem(
            "Salmon en papillote",
            25,
            "Food",
            "Salmon baked in parchment with herbs.", R.drawable.salmon_en_papillote
        ),
        ProductItem(
            "Quiche Lorraine",
            17,
            "Dessert",
            "A rich, creamy quiche with bacon and cheese.", R.drawable.quiche_lorraine
        ),
        ProductItem(
            "Custard tart",
            14,
            "Dessert",
            "A sweet, creamy custard tart.", R.drawable.custard_tart
        ),
        ProductItem(
            "Croissant",
            7,
            "Dessert",
            "Flaky and buttery croissant.", R.drawable.croissant
        ),
        ProductItem(
            "Greek Salad",
            12,
            "Side",
            "The famous Greek salad of crispy lettuce, peppers, olives and our Chicago...",
            R.drawable.greeksalad
        ),
        ProductItem(
            "Bruschetta",
            6,
            "Side",
            "Our Bruschetta is made from grilled bread that has been smeared with garlic...",
            R.drawable.bruschetta
        ),
        ProductItem(
            "Lemon Dessert",
            9,
            "Dessert",
            "This comes straight from grandma's recipe book, every last ingredient has...",
            R.drawable.lemondessert
        )
    )



    val offersList = mutableListOf(
        ProductItem(
            "Black tea",
            3,
            "Drinks",
            "A strong, aromatic black tea.",
            R.drawable.black_tea
        ),
        ProductItem(
            "Green tea",
            3,
            "Drinks",
            "A refreshing green tea with a light taste.",
            R.drawable.green_tea
        ),

    )
}

