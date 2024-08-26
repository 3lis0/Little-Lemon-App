package com.example.littlelemon.Screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DatePicker
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Popup
import androidx.navigation.NavController
import com.example.littlelemon.Components.Counter
import com.example.littlelemon.Database.Categories
import com.example.littlelemon.MyNavigation
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor
import com.example.littlelemon.ui.theme.LittleLemonTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReserveTableScreen(navController: NavController){
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xFFFFFFFF)
    ) {

        val configuration = LocalConfiguration.current
        val screenWidth = configuration.screenWidthDp.dp

        Column(
            modifier = Modifier
                .width(screenWidth)
                .height(300.dp)
        ) {
            Box(
                modifier = Modifier
                    .width(screenWidth)
            ) {
                val imageModifier = Modifier
                    .width(screenWidth)
                    .height(300.dp)
                Image( // Use Image composable for displaying images
                    painter = painterResource(id = R.drawable.reserve_table), // Access image property
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds,
                    modifier = imageModifier
                )
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(300.dp)
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(Color(244, 206, 20,), Color(73, 94, 87,)),
                            ),
                            alpha = 0.7f
                        )
                        .padding(bottom = 10.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = Color(0xFFFFFFFF),
                        modifier = Modifier
                            .padding(20.dp)
                            .size(30.dp)
                            .clickable {
                                navController.popBackStack()
                            }
                    )
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = "Reserve -\nyour table".uppercase(),
                            style = TextStyle(
                                fontSize = 32.sp,
                                fontFamily = FontFamily(Font(R.font.karla)),
                                fontWeight = FontWeight(700),
                                color = Color(0xFFFFFFFF),
                            ),
                        )
                    }


                }
            }


            var username by rememberSaveable { mutableStateOf("") }

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
            ) {
                Text(
                    text = "Yor reservation name:",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.karla)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier.fillMaxWidth()
                )

                OutlinedTextField(
                    value = username,
                    onValueChange = { username = it },
                    maxLines = 1,
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = Color(0xFFF1F1F1),
                        focusedContainerColor = Color(0xFFF1F1F1),
                        focusedIndicatorColor = Color(0xFFF4CE14),
                        unfocusedIndicatorColor = Color(0xFFCCCCCC),
                        cursorColor = Color(0xFF000000)
                    ),
                    shape = RoundedCornerShape(size = 10.dp),
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .height(50.dp)
                        .fillMaxWidth(),
                    trailingIcon = {
                            IconButton(onClick = {username = ""}) {
                                Icon(
                                    tint = Color(0xFFA3A3A3),
                                    //modifier =,
                                    imageVector = Icons.Default.Clear , contentDescription = null
                                )
                            }
                    }
                )

                Text(
                    text = "Date:",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.karla)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp)
                )

                DatePickerDocked()

                Text(
                    text = "Time:",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.karla)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 10.dp)
                )
                var selectedTime by remember {
                    mutableStateOf("")
                }
                val times = listOf(
                    "6:00 PM", "6:30 PM", "7:00 PM", "7:30 PM",
                    "8:00 PM", "8:30 PM", "9:00 PM", "9:30 PM",
                    "10:00 PM", "10:30 PM", "11:00 PM", "11:30 PM",
                    "12:00 AM"
                )
                LazyRow(
                    state = rememberLazyListState(),
                ) {
                    items(times) { time ->
                        Button(
                            onClick = {
                                selectedTime = time
                            },
                            colors = ButtonDefaults.buttonColors(
                                containerColor =  if (time == selectedTime) {
                                    LittleLemonColor.green} else Color.LightGray
                            ),
                            shape = RoundedCornerShape(25),
                            modifier = Modifier.padding(end = 5.dp)
                        ) {
                            Text(
                                text = time,
                                color = if (time == selectedTime) { Color.White} else LittleLemonColor.green
                            )
                        }
                    }
                }

                Text(
                    text = "How many person?",
                    style = TextStyle(
                        fontSize = 20.sp,
                        fontFamily = FontFamily(Font(R.font.karla)),
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, bottom = 20.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Counter(30,30,50,40,0)
                }


                var doneButton by remember {
                    mutableStateOf(false)
                }
                Button(
                    modifier = Modifier
                        .padding(top = 30.dp)
                        .fillMaxWidth()
                        .height(50.dp),
                    onClick = {
                        doneButton = true
                    },
                    colors = ButtonDefaults.buttonColors(
                        containerColor =  if (doneButton) {
                            LittleLemonColor.yellow} else Color.Black
                    ),
                    shape = RoundedCornerShape(10),
                ) {
                    Text(
                        text = if (doneButton) "Done" else "Book the table",
                        color = Color.White
                    )
                }

            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerDocked() {

    var showDatePicker by remember { mutableStateOf(false) }
    val datePickerState = rememberDatePickerState()

    val selectedDate = datePickerState.selectedDateMillis?.let {
        convertMillisToDate(it)
    } ?: ""

    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            shape = RoundedCornerShape(size = 10.dp),
            value = selectedDate,
            onValueChange = { },
            label = {
                Text(text ="Pick the data", modifier = Modifier.clickable { showDatePicker = true })
                    },
            //enabled = false,
            readOnly = true,
            trailingIcon = {
                IconButton(onClick = { showDatePicker = !showDatePicker }) {
                    Icon(
                        imageVector = Icons.Default.DateRange,
                        contentDescription = "Select date"
                    )
                }
            },
            colors = TextFieldDefaults.colors(
                unfocusedContainerColor = Color(0xFFF1F1F1),
                focusedContainerColor = Color(0xFFF1F1F1),
                focusedIndicatorColor = Color(0xFFF4CE14),
                unfocusedIndicatorColor = Color(0xFFCCCCCC),
                ),
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp)
                .clickable {
                    showDatePicker = true
                },

        )

        if (showDatePicker) {
            Popup(
                onDismissRequest = { showDatePicker = false },
                alignment = Alignment.TopStart
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 64.dp)
                        .shadow(elevation = 4.dp)
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                ) {
                    Row (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.End
                    ){
                        Icon(
                            modifier = Modifier
                                .size(25.dp)
                                .clickable { showDatePicker = false },
                            tint = Color.Black,
                            imageVector = Icons.Default.Check , contentDescription = null
                        )
                    }

                    DatePicker(
                        state = datePickerState,
                        showModeToggle = false
                    )
                }
            }
        }
    }
}

fun convertMillisToDate(millis: Long): String {
    val formatter = SimpleDateFormat("MM/dd/yyyy", Locale.getDefault())
    return formatter.format(Date(millis))
}



@Preview(showBackground = true)
@Composable
fun ReserveTableScreenPreview() {
    LittleLemonTheme {
        //ReserveTableScreen()
    }
}