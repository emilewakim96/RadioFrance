package com.example.radiofrance.shows

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import com.example.presentation.shows.ShowsViewModel
import com.example.radiofrance.R
import com.example.radiofrance.common.ErrorScreen
import com.example.radiofrance.common.Loader
import org.koin.androidx.compose.koinViewModel

@Composable
fun ShowsScreen(
    navController: NavHostController,
    viewModel: ShowsViewModel = koinViewModel<ShowsViewModel>()
) {

    val state = viewModel.state.collectAsStateWithLifecycle().value
    val context = LocalContext.current

    when {
        state.isError -> {
            ErrorScreen(context.getString(R.string.default_error))
        }

        state.isLoading -> {
            Loader()
        }

        else -> {
            LazyColumn(
                modifier = Modifier
                    .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
                    .fillMaxSize()
                    .padding(horizontal = 30.dp, vertical = 30.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
            ) {
                val items = state.items
                item {
                    Text(
                        modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                        text = state.title,
                        style = MaterialTheme.typography.headlineMedium
                    )
                }
                items(state.items.size) { index ->
                    val item = items[index]
                    ShowCard(item = item)
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