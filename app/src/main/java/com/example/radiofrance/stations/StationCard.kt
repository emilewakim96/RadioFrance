package com.example.radiofrance.stations

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.presentation.model.StationItem

@Composable
fun StationCard(item: StationItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .background(color = MaterialTheme.colorScheme.surfaceContainerLow)
            .clickable {
                onClick()
            }
            .fillMaxWidth()
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = item.title, style = MaterialTheme.typography.bodySmall)
            item.description?.let { Text(text = it, style = MaterialTheme.typography.bodyMedium) }
//            Button(
//                onClick = {
//                    item.changeColorAction.execute(Color.Red)
//                }
//            ) {
//                Text(text = "Changer la couleur")
//            }
        }
    }
}