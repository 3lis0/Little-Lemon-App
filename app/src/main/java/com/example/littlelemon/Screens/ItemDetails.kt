package com.example.littlelemon.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.littlelemon.AppFunctions.MenuItemsViewModel
import com.example.littlelemon.Basket
import com.example.littlelemon.Components.Counter
import com.example.littlelemon.Database.BasketList
import com.example.littlelemon.Database.LoadAndDisplayProducts
import com.example.littlelemon.Database.getImageResourceById
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun ItemDetailsScreen(orderNo: Int?, navController: NavHostController, viewModel: MenuItemsViewModel){
    val context = LocalContext.current
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val localList = LoadAndDisplayProducts()
    val itamDetails = localList.filter { it.id==orderNo }[0]

    var amount by remember {
        mutableIntStateOf(0)
    }

    Surface(
        color = Color(0xFFFFFFFF),
        modifier = Modifier.fillMaxSize()
    ) {
        Column {
            val imageModifier = Modifier
                .width(screenWidth)
                .height(300.dp)
            Image( // Use Image composable for displaying images
                painter = painterResource(getImageResourceById(itamDetails.id)), // Access image property
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = imageModifier
            )
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,

            ) {
                Column {
                    Text(
                        text = itamDetails.name,
                        style = TextStyle(
                            fontSize = 28.sp,
                            fontFamily = FontFamily(Font(R.font.karla)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                    )

                    Text(
                        text = itamDetails.description,
                        style = TextStyle(
                            fontSize = 16.sp,
                            fontFamily = FontFamily(Font(R.font.karla)),
                            fontWeight = FontWeight(500),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 5.dp)
                    )

                    Text(
                        text = "Price: $${itamDetails.price}",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontFamily = FontFamily(Font(R.font.karla)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 20.dp)
                    )
                }



                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = "How many ${itamDetails.name} you want?",
                        style = TextStyle(
                            fontSize = 18.sp,
                            fontFamily = FontFamily(Font(R.font.karla)),
                            fontWeight = FontWeight(600),
                            color = Color(0xFF000000),
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 40.dp)
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        amount = Counter(30, 30, 50, 40, 0)
                    }
                }


                Column {

                    val basketList = BasketList(itamDetails.id, amount)
                    var doneButton by remember {
                        mutableStateOf(false)
                    }

                    Button(
                        modifier = Modifier
                            .padding(top = 30.dp)
                            .fillMaxWidth()
                            .height(50.dp),
                        onClick = {

                            if (amount <= 0){
                                Toast.makeText(context, "You should determine the amount of ${itamDetails.name} that you want!!", Toast.LENGTH_SHORT).show()
                            } else {
                                viewModel.addMenuItem(basketList)
                                doneButton = true
                            }

                        },
                        colors = ButtonDefaults.buttonColors(
                            containerColor = if (doneButton) {
                                LittleLemonColor.yellow
                            } else Color.Black
                        ),
                        shape = RoundedCornerShape(10),
                    ) {
                        Text(
                            text = if (doneButton) "Done" else "Add to Basket",
                            color = Color.White
                        )
                    }

                    Row (
                        modifier = Modifier.fillMaxWidth().padding(top = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ){
                        Button(
                            modifier = Modifier
                                .weight(0.5f)
                                .height(50.dp),
                            onClick = {
                                navController.popBackStack()
                            },
                            colors = ButtonDefaults.buttonColors(
                                Color.Black
                            ),
                            shape = RoundedCornerShape(10),
                        ) {
                            Icon(
                                imageVector = Icons.Default.ArrowBack,
                                tint = Color.White,
                                contentDescription = "back",
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                            Text(
                                text = "Back",
                                color = Color.White
                            )
                        }

                        Button(
                            modifier = Modifier
                                .weight(0.5f)
                                .height(50.dp),
                            onClick = {
                               navController.navigate(Basket.route)
                            },
                            colors = ButtonDefaults.buttonColors(
                                LittleLemonColor.green
                            ),
                            shape = RoundedCornerShape(10),
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.icons8_shopping_basket_48),
                                tint = Color.White,
                                contentDescription = "back",
                                modifier = Modifier
                                    .padding(5.dp)
                            )
                            Text(
                                text = "Basket",
                                color = Color.White
                            )
                        }
                    }
                }
            }
        }

    }
}