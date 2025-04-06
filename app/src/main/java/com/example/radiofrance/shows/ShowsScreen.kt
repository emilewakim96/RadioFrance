package com.example.radiofrance.shows

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowsScreen(
    navController: NavHostController,
    itemId: String,
    viewModel: ShowsViewModel = koinViewModel<ShowsViewModel>()
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
                    .fillMaxSize()
                    .padding(25.dp)
            ) {
                val items = state.items
                item {
                    Text(
                        modifier = Modifier.padding(15.dp),
                        text = state.title,
                        style = MaterialTheme.typography.headlineSmall
                    )
                }
                items(state.items.size) { index ->
                    val item = items[index]
                    ShowCard(item = item) {
                        // TODO on click show Item
                    }
                }
            }
        }
    }

    IconButton(
        onClick = { navController.popBackStack() },
        modifier = Modifier.padding(top = 32.dp)
    ) {
        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
    }
}