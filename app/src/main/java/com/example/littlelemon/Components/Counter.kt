package com.example.littlelemon.Components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.littlelemon.R
import com.example.littlelemon.ui.theme.LittleLemonColor

@Composable
fun Counter(endPadding: Int, startPadding: Int, sizeBox: Int, fontSize:Int, currentCount:Int = 0): Int {
    var counter by remember { mutableIntStateOf(currentCount) }

    Row (
        verticalAlignment = Alignment.CenterVertically,
    ){

        Box(
            modifier = Modifier
                .padding(end = endPadding.dp)//30
                .clip(CircleShape)
                .background(LittleLemonColor.yellow)
                .size(sizeBox.dp)
                .clickable {
                    if (counter > 0) {
                        counter -= 1
                    }
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = "Plus",
                tint = Color.Black
            )
        }

        Box(
            modifier = Modifier
                .clip(CircleShape)
                .background(color = Color(0x33F4CE14))
                .size(sizeBox.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = counter.toString(),
                style = TextStyle(
                    fontSize = fontSize.sp,
                    fontFamily = FontFamily(Font(R.font.karla)),
                    fontWeight = FontWeight(500),
                    color = Color(0xFF000000),
                )
            )
        }

        Box(
            modifier = Modifier
                .padding(start = startPadding.dp)
                .clip(CircleShape)
                .background(LittleLemonColor.yellow)
                .size(sizeBox.dp)
                .clickable {
                    counter++
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                imageVector = Icons.Default.KeyboardArrowUp,
                contentDescription = "Plus",
                tint = Color.Black
            )
        }
    }
    return counter
}