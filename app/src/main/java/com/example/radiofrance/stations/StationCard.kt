package com.example.radiofrance.stations

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presentation.model.StationItem

@Composable
fun StationCard(item: StationItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                onClick()
            },
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(text = item.title, style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold))
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