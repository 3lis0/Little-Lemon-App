package com.example.littlelemon.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineBreak
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import com.example.littlelemon.AppFunctions.MenuItemsViewModel
import com.example.littlelemon.Components.Counter
import com.example.littlelemon.Components.topAppBar
import com.example.littlelemon.Database.BasketList
import com.example.littlelemon.Database.BasketOrderList
import com.example.littlelemon.Database.LoadAndDisplayProducts
import com.example.littlelemon.Database.MenuJsonList
import com.example.littlelemon.Database.getImageResourceById
import com.example.littlelemon.MyBottomNavigation
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor
import java.time.temporal.TemporalAmount

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun BasketScreen(navController: NavController, viewModel: MenuItemsViewModel) {

    val list by viewModel.menuItems.observeAsState(emptyList())

    var localList = LoadAndDisplayProducts()


    var itmesNum by remember {
        mutableIntStateOf(0)
    }

    var totalPrice by remember {
        mutableIntStateOf(0)
    }


    val orderItem = localList.fold(mutableListOf<BasketOrderList>()){ newLi, item ->
        newLi.apply {
            list.forEach(){ item2 ->
                if (item2.id == item.id){
                    add(BasketOrderList(item.id,item2.amount,item.name,(item.price * item2.amount)))
                }
            }
        }
    }

    itmesNum = orderItem.sumOf { it.amount }
    totalPrice = orderItem.sumOf { it.price }

//    var changeiItmesNum by remember { mutableIntStateOf(0) }
//    var changeiTotalPrice by remember { mutableIntStateOf(0) }

    Scaffold (
      bottomBar = {
          Column {
              ProceedCheckout(itmesNum, totalPrice)
              MyBottomNavigation(navController = navController, 2)
          }

        }
    ) {
       Column(
           modifier = Modifier
               .fillMaxSize()
               .padding(20.dp)
       ) {
           LazyColumn(
               state = rememberLazyListState(),
           ) {
               items(orderItem){ item ->
                   BasketItemsCard(item, viewModel)
               }
           }
       }
    }

}

@Composable
fun BasketItemsCard(item: BasketOrderList, viewModel: MenuItemsViewModel){

    var changeInAmount by remember { mutableIntStateOf(0) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxHeight()
        ) {

            Image(
                painter = painterResource(id = getImageResourceById(item.id)),
                contentDescription = "",
                contentScale = ContentScale.FillBounds,
                modifier = Modifier
                    .size(120.dp)
                    .clip(shape = RoundedCornerShape(10.dp))

                )

            Column(
                modifier = Modifier
                    .height(120.dp)
                    .padding(start = 15.dp),
                verticalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = item.name,
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.karla)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                        lineBreak = LineBreak.Simple
                    ),
                    modifier = Modifier.width(160.dp)

                )

                changeInAmount = Counter(5,5,30,20,item.amount)
                viewModel.updateItemAmountById(item.id,changeInAmount)

                Text(
                    text = "Total: $${item.price * changeInAmount}",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.karla)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    ),
                )
            }
        }

        Column(
            modifier = Modifier.height(120.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.End
        ) {
            Text(
                text = "X${changeInAmount}",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                ),
            )

            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete",
                tint = LittleLemonColor.green,
                modifier = Modifier.size(25.dp)
                    .clickable {
                        viewModel.deleteItemById(item.id)
                    }
                )

        }
    }
    HorizontalDivider(
        modifier = Modifier.padding(8.dp),
        thickness = 1.dp,
        color = Color.Gray
    )
}

@Composable
fun ProceedCheckout(amount: Int, price: Int){

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(243, 243, 243, 255))
            .padding(20.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.padding(end = 20.dp)
        ) {
            Text(
                text = "USD ${price}",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontWeight = FontWeight(600),
                    color = Color(0xFF000000),
                ),
            )
            Text(
                text = "$amount items",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontWeight = FontWeight(600),
                    color = Color.Gray,
                ),
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            modifier = Modifier
                .fillMaxWidth()
                .height(60.dp),
            onClick = {
            },
            colors = ButtonDefaults.buttonColors(
                LittleLemonColor.yellow
            ),
            shape = RoundedCornerShape(20),
        ) {
            Text(
                text = "Proceed to checkout",
                color = Color.Black,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontWeight = FontWeight(600),
                    color = Color.Black,
                )
            )
            Icon(
                imageVector = Icons.Default.ArrowForward,
                tint = Color.Black,
                contentDescription = "ArrowForward"
            )
        }
    }
}