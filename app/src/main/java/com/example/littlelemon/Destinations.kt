package com.example.littlelemon

import android.widget.Button
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource

interface Destinations {
    val route: String
    val icon: Int
    val title: String
}

object Home: Destinations{
    override val route = "Home"
    override val icon = R.drawable.ic_home
    override val title = "Home"
}

object Menu: Destinations{
    override val route = "Menu"
    override val icon = R.drawable.ic_menu
    override val title = "Menu"
}

object Basket : Destinations{
    override val route = "Basket"
    override val icon = R.drawable.icons8_shopping_basket_48
    override val title = "Basket"
}

object Reserve : Destinations{
    override val route = "Reserve"
    override val icon = 0
    override val title = "Reserve"
}

object Details : Destinations{
    override val route = "Details"
    override val icon = 0
    override val title = "Details"
    const val argOrderNo = "OrderNo"
}