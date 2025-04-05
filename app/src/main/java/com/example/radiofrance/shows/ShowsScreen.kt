package com.example.radiofrance.shows

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController

@Composable
fun ShowsScreen(navController: NavHostController, itemId: String) {

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(20.dp),
            text = "Details for Item $itemId"
        )

        IconButton(onClick = { navController.popBackStack() }) {
            Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
        }
    }

}