package com.example.radiofrance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.radiofrance.navigation.Route
import com.example.radiofrance.shows.ShowsScreen
import com.example.radiofrance.stations.StationsScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavigation()
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Route.Stations
    ) {
        composable<Route.Stations> {
            StationsScreen(navController)
        }
        composable<Route.Shows> {
            ShowsScreen(navController)
        }
    }
}
