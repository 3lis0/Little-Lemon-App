package com.example.littlelemon.Components

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DrawerState
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun DrawerPanel(drawerState: DrawerState, scope: CoroutineScope){
    List(10){
        Text(
            text = "item $it",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp),
        )
    }
//    IconButton(onClick = {
//        scope.launch {
//            drawerState.apply {
//                close()
//            }
//        }
//    }
//    ) {
//        Icon(imageVector = Icons.Default.ArrowBack, contentDescription ="close" )
//    }
}
