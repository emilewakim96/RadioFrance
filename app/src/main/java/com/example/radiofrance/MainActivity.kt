package com.example.radiofrance

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.radiofrance.navigation.Route
import com.example.radiofrance.shows.ShowsScreen
import com.example.radiofrance.stations.StationsScreen
import com.example.radiofrance.ui.theme.RadioFranceTheme

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
        composable<Route.Shows> { backStackEntry ->
            val itemId = backStackEntry.arguments?.getString(Route.Shows::stationId.name)
            itemId?.let { ShowsScreen(navController, it) }
        }
    }
}
