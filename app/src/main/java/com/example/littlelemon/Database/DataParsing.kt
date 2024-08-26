package com.example.littlelemon.Database

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LifecycleCoroutineScope
import com.example.littlelemon.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.io.BufferedReader

@Serializable
data class MenuJsonList(
    @SerialName("productsList")
    val menu: List<MenuItemsJson>,
) {
    @Serializable
    data class MenuItemsJson(
        @SerialName("id")
        val id: Int,
        @SerialName("name")
        val name: String,
        @SerialName("price")
        val price: Int,
        @SerialName("category")
        val category: String,
        @SerialName("description")
        val description: String,
    )
}

// Extension function to convert MenuItemsJson to MenuItemRoom
fun MenuJsonList.MenuItemsJson.toMenuItemRoom(): MenuItemRoom {
    return MenuItemRoom(
        id = this.id,
        name = this.name,
        price = this.price,
        category = this.category,
        description = this.description
    )
}

fun loadJSONFromAsset(context: Context, fileName: String): String? {
    return try {
        val inputStream = context.assets.open(fileName)
        val bufferedReader = BufferedReader(inputStream.reader())
        bufferedReader.use { it.readText() }
    } catch (ex: Exception) {
        ex.printStackTrace()
        null
    }
}

@Composable
fun LoadAndDisplayProducts(): List<MenuJsonList.MenuItemsJson> {

    val context = LocalContext.current
    val jsonData = loadJSONFromAsset(context, "productsList.json")

    // Continue with parsing the JSON and displaying it
    val gson = Gson()
    val typeToken = object : TypeToken<Map<String, List<MenuJsonList.MenuItemsJson>>>() {}.type

    val productsMap: Map<String, List<MenuJsonList.MenuItemsJson>> = gson.fromJson(jsonData, typeToken)
    val productsJsonList = productsMap["productsList"]

    return productsJsonList!!
}

@Composable
fun LoadAndSaveProducts(lifecycleScope: LifecycleCoroutineScope) {
    val context = LocalContext.current
    val jsonData = loadJSONFromAsset(context, "productsList.json")

    jsonData?.let { data ->
        val gson = Gson()
        val typeToken = object : TypeToken<Map<String, List<MenuJsonList.MenuItemsJson>>>() {}.type
        val productsMap: Map<String, List<MenuJsonList.MenuItemsJson>> = gson.fromJson(data, typeToken)
        val productsJsonList: List<MenuJsonList.MenuItemsJson>? = productsMap["productsList"]

        productsJsonList?.let { productList ->
            val database = AppDatabase.getDatabase(context)
            val menuItemsRoom = productList.map { it.toMenuItemRoom() }

            // Save data to Room in a coroutine using the passed lifecycleScope
            lifecycleScope.launch(Dispatchers.IO) {
                if (database.menuItemDao().isEmpty()) {
                    database.menuItemDao().insertAll(*menuItemsRoom.toTypedArray())
                }
            }
        }
    }
}


