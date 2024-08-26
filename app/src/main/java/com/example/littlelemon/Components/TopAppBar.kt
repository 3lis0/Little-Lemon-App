package com.example.littlelemon.Components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layout
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.example.littlelemon.R
import kotlinx.coroutines.launch

@Composable
fun topAppBar(){
/*
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl ) {
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                DrawerPanel(drawerState, scope)
            }
        },
    ) {}

    }*/
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .background(Color(255, 255, 255))
            .fillMaxWidth()
            .height(70.dp)
            .padding(15.dp)
    )
    {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "logo",
            modifier = Modifier.height(40.dp)
        )

        Icon(
            painter = painterResource(id = R.drawable.icons8_hamburger_menu__1_),
            contentDescription = "menu icon",
            modifier = Modifier
                .height(40.dp)
                .width(40.dp)
                .clickable {
                   /*scope.launch {
                       if (drawerState.isClosed) drawerState.apply { open() } else drawerState.apply { close() }
                    }*/
                },
            tint = Color(0xFF495E57)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun topAppBarPreview() {
    topAppBar()
}