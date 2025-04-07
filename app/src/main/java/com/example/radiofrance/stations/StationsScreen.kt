package com.example.radiofrance.stations

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.presentation.stations.StationsViewModel
import com.example.radiofrance.R
import com.example.radiofrance.common.ErrorScreen
import com.example.radiofrance.common.Loader
import com.example.radiofrance.navigation.Route
import org.koin.androidx.compose.koinViewModel

@Composable
internal fun StationsScreen(
    navController: NavHostController,
    viewModel: StationsViewModel = koinViewModel<StationsViewModel>()
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    when {
        state.error != null -> {
            ErrorScreen(state.error?.message ?: context.getString(R.string.default_error))
        }

        state.isLoading -> {
            Loader()
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                    .fillMaxSize()
                    .padding(horizontal = 20.dp, vertical = 30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                val items = state.items
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp),
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
