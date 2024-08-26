package com.example.littlelemon.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.AppFunctions.MenuItemsViewModel
import com.example.littlelemon.AppFunctions.filterProducts
import com.example.littlelemon.Database.Categories
import com.example.littlelemon.Database.LoadAndDisplayProducts
import com.example.littlelemon.Database.MenuJsonList
import com.example.littlelemon.Database.getImageResourceById
import com.example.littlelemon.Details
import com.example.littlelemon.MyBottomNavigation
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTheme

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MenuListScreen(navController: NavController) {
    Scaffold (
        bottomBar = {
            MyBottomNavigation(navController = navController, 1)
        }
    ) {
        Surface(
            color = Color(0xFFFFFFFF)
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                UpperPanel()
                LowerPanel(navController)
            }
        }
    }
}

@Composable
private fun UpperPanel() {
    Text(
        text = "Little Lemon Menu",
        fontSize = 24.sp,
        fontWeight = Bold,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        color = LittleLemonColor.green

    )
}

@Composable
private fun LowerPanel(navController: NavController) {
    var selectedCategory by remember {
        mutableStateOf("All")
    }

    Column {
        LazyRow(
            state = rememberLazyListState(),
            modifier = Modifier.padding(start = 15.dp, end = 15.dp)
        ) {
            items(Categories) { category ->
                Button(
                    onClick = {
                        selectedCategory = category
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  if (category == selectedCategory) {
                            LittleLemonColor.green} else Color.LightGray
                    ),
                    shape = RoundedCornerShape(40),
                    modifier = Modifier.padding(5.dp)
                ) {
                    Text(
                        text = category,
                        color = if (category == selectedCategory) { Color.White} else LittleLemonColor.green
                    )
                }
            }
        }
        HorizontalDivider(
            modifier = Modifier.padding(8.dp),
            thickness = 1.dp,
            color = Color.Gray
        )
        val listFromJson = LoadAndDisplayProducts()
        LazyColumn(
            modifier = Modifier.padding(horizontal = 20.dp),
            state = rememberLazyListState()
        ) {
            val productsFilterdList = filterProducts(selectedCategory,listFromJson)
            items(productsFilterdList) { item ->
                MenuDish(item, navController)
            }
        }
    }
}



@Composable
fun MenuDish(item: MenuJsonList.MenuItemsJson, navController: NavController) {
    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        border = BorderStroke( 1.dp, Color(0x11000000)),
        modifier = Modifier
            .height(130.dp)
            .padding(bottom = 10.dp)
            .clickable {
                navController.navigate(Details.route + "/${item.id}")
            }
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Image(
                painter = painterResource(id = getImageResourceById(item.id)),
                contentDescription = "",
                modifier = Modifier
                    .width(130.dp)
                    .fillMaxHeight()
                    .clip(shape = RoundedCornerShape(topStart = 10.dp, bottomStart = 10.dp)),
                contentScale = ContentScale.FillBounds,
            )

            Column(
                verticalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 5.dp)
            ) {
                Column {

                    Text(
                        text = item.name,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                    Text(
                        text = item.description,
                        color = Color.Gray,
                        fontSize = 12.sp,
                        lineHeight = 14.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth(.90f)
                    )
                }
                Row (
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.Bottom
                ){
                    Text(
                        text = "$${item.price}",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold
                    )
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                        contentDescription = "",
                        tint = LittleLemonColor.green,
                        modifier = Modifier.size(25.dp)
                    )
                }

            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun MenuListScreenPreview() {
    LittleLemonTheme {
        //MenuListScreen()
    }
}