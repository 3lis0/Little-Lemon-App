package com.example.littlelemon.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.littlelemon.AppFunctions.filterProducts
import com.example.littlelemon.R
import com.example.littlelemon.Components.topAppBar
import com.example.littlelemon.Database.LoadAndDisplayProducts
import com.example.littlelemon.Database.MenuJsonList
import com.example.littlelemon.Database.getImageResourceById
import com.example.littlelemon.MyBottomNavigation
import com.example.littlelemon.Reserve
import com.example.littlelemon.ui.theme.LittleLemonColor

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController){

    Scaffold (
        topBar = {
            topAppBar()
        },

        bottomBar = {
            MyBottomNavigation(navController = navController, 0)
        }
    ) {

        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Color(0xFFF5FFE5)
        ) {

            val configuration = LocalConfiguration.current
            val screenWidth = configuration.screenWidthDp.dp
            Column(
                modifier = Modifier
                    .width(screenWidth)
            ) {
                Box(
                    modifier = Modifier
                        .width(screenWidth)
                        .height(400.dp)
                ) {
                    val imageModifier = Modifier
                        .width(screenWidth)
                    Image( // Use Image composable for displaying images
                        painter = painterResource(id = R.drawable.header_background), // Access image property
                        contentDescription = "",
                        contentScale = ContentScale.FillWidth,
                        modifier = imageModifier
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(400.dp)
                            .background(
                                brush = Brush.verticalGradient(
                                    colors = listOf(Color(0, 0, 0), Color(0xFF495E57)),
                                ),
                                alpha = 0.9f
                            )
                    )


                    Column(
                        horizontalAlignment = Alignment.Start,
                        verticalArrangement = Arrangement.SpaceBetween,
                        modifier = Modifier
                            .padding(top = 90.dp, start = 20.dp)
                            .width(320.dp)
                            .height(300.dp)
                    ) {
                        Column {
                            Text(
                                text = "Little Lemon",
                                // Display_serif
                                style = TextStyle(
                                    fontSize = 64.sp,
                                    fontFamily = FontFamily(Font(R.font.markazi_text)),
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFF4CE14),
                                ),
                                modifier = Modifier.padding(bottom = 0.dp)
                            )
                            Text(
                                text = "Chicago",
                                // Subheading
                                style = TextStyle(
                                    fontSize = 40.sp,
                                    fontFamily = FontFamily(Font(R.font.markazi_text)),
                                    fontWeight = FontWeight(400),
                                    color = Color(0xFFEDEFEE),
                                ),
                                modifier = Modifier.offset(y = (-20).dp)
                            )
                        }

                        Text(
                            text = "We are a family owned Mediterranean restaurant, focused on traditional recipes served with a modern twist.",
                            style = TextStyle(
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.karla)),
                                fontWeight = FontWeight(500),
                                color = Color(0xFFFFFFFF),
                            )
                        )

                        Button(
                            onClick = { navController.navigate(Reserve.route) },
                            modifier = Modifier
                                .width(160.dp)
                                .height(60.dp)
                                .padding(bottom = 20.dp),
                            colors = ButtonDefaults.buttonColors(
                                containerColor = Color(0xFFF4CE14)
                            ),
                            shape = RoundedCornerShape(size = 15.dp),

                            ) {
                            Text(
                                text = "Reserve a table",

                                // Nav_Link
                                style = TextStyle(
                                    fontSize = 16.sp,
                                    fontFamily = FontFamily(Font(R.font.karla)),
                                    fontWeight = FontWeight(700),
                                    color = Color(0xFF000000),
                                )
                            )
                        }
                    }
                }
                HomeLowerPanel()
            }
        }
    }
}



@Composable
fun HomeLowerPanel(){
    Column(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, top = 20.dp)
            .fillMaxSize(),
    ) {
        Text(
            text = "Offer of this week!!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Left,
            modifier = Modifier
                .fillMaxWidth(),
            color = Color.Black
        )

        Column(
            Modifier.padding(top = 15.dp),
        ) {
            val listFromJsonOffer = LoadAndDisplayProducts()
            LazyColumn(
                state = rememberLazyListState()
            ) {
                val productsFilterdList = filterProducts("Dessert",listFromJsonOffer)
                items(productsFilterdList) { item ->
                    MenuOfferItemsList(item)
                }
            }


        }
    }
}

@Composable
fun MenuOfferItemsList(item: MenuJsonList.MenuItemsJson) {

    Card (
        colors = CardDefaults.cardColors(
            containerColor = Color(0xFFFFFFFF)
        ),
        border = BorderStroke( 1.dp, Color(0x11000000)),
        modifier = Modifier
            .height(130.dp)
            .padding(bottom = 10.dp)
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

            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 10.dp, bottom = 10.dp, end = 15.dp)
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
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth(.90f)
                    )
                Row(
                    modifier = Modifier.fillMaxHeight().fillMaxWidth(),
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.End
                ) {
                    Text(
                        text = "$${item.price}",
                        fontSize = 12.sp,
                        color = Color.Gray,
                        fontWeight = FontWeight.Bold,
                        textDecoration = TextDecoration.LineThrough,

                        )
                    Text(
                        text = "$${item.price-2}",
                        fontSize = 20.sp,
                        color = Color.Red,
                        fontWeight = FontWeight.Bold
                    )
                }

                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    //HomeScreen()
}
