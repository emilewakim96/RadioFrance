package com.example.radiofrance.stations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.radiofrance.navigation.Route
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun StationsScreen(
    navController: NavHostController,
    viewModel: StationsViewModel = koinViewModel<StationsViewModel>()
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value
    when {
        state.isError -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Loading"
                )
            }
        }

        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize()) {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "Loading"
                )
            }
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 40.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                val items = state.items
                item {
                    Text(
                        text = state.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                items(state.items.size) { index ->
                    val item = items[index]
                    StationCard(item = item) {
                        navController.navigate(Route.Shows(item.id))
                    }
                }
            }
        }
    }
}
