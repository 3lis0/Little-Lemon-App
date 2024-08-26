package com.example.littlelemon

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.littlelemon.AppFunctions.MenuItemsViewModel
import com.example.littlelemon.Database.AppDatabase
import com.example.littlelemon.Database.LoadAndDisplayProducts
import com.example.littlelemon.Database.LoadAndSaveProducts
import com.example.littlelemon.Database.MenuJsonList
import com.example.littlelemon.Database.loadJSONFromAsset
import com.example.littlelemon.Screens.BasketScreen
import com.example.littlelemon.Screens.HomeScreen
import com.example.littlelemon.Screens.ItemDetailsScreen
import com.example.littlelemon.Screens.MenuListScreen
import com.example.littlelemon.Screens.ReserveTableScreen
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTheme
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
           LittleLemonTheme {
               MyNavigation()
           }

        }

    }
}

@Composable
fun MyNavigation() {

    val navController = rememberNavController()
    val viewModel = MenuItemsViewModel()

    Scaffold(
       ) {
        Box(Modifier.padding(it)) {
            NavHost(
                navController = navController,
                startDestination = Home.route
            ) {
                composable(Home.route) {
                    HomeScreen(navController = navController)
                }
                composable(Menu.route) {
                    MenuListScreen(navController = navController)
                }
                composable(Basket.route) {
                    BasketScreen(navController = navController, viewModel)
                }
                composable(Reserve.route) {
                    ReserveTableScreen(navController = navController)
                }

                composable(
                    Details.route + "/{${Details.argOrderNo}}",
                    arguments = listOf(navArgument(Details.argOrderNo) { type = NavType.IntType}
                    )) {
                    ItemDetailsScreen(it.arguments?.getInt(Details.argOrderNo), navController, viewModel)
                }

            }
        }
    }
}

@Composable
fun MyBottomNavigation(navController: NavController, screenNum: Int){
    val destinationList = listOf<Destinations>(
        Home,
        Menu,
        Basket
    )

    var selectedIndex by remember {
        mutableIntStateOf(screenNum)
    }

    NavigationBar(
        modifier = Modifier.border(1.dp, Color(0x11000000)),
        containerColor = Color.White
    )  {
        destinationList.forEachIndexed { index, destination ->
            NavigationBarItem(
                selected = index == selectedIndex,
                label = { Text(text = destination.title) },
                icon = {
                    Icon(
                        painter = painterResource(id = destination.icon),
                        contentDescription = destination.title,
                        modifier = Modifier.size(28.dp)
                    )
                },
                onClick = {
                    selectedIndex = index
                    navController.navigate(destination.route) {
                        popUpTo(navController.graph.findStartDestination().id){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = LittleLemonColor.green,
                    indicatorColor = LittleLemonColor.yellow.copy(alpha = 0.3f)
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LittleLemonTheme {
        MyNavigation()
    }
}