package com.example.radiofrance.shows

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presentation.model.ShowItem

@Composable
fun ShowCard(item: ShowItem) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = item.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )

            item.standFirst?.let {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item.url?.takeIf { item.seeUrl.text.isNotEmpty() }?.let {
                Text(
                    modifier = Modifier
                        .padding(4.dp)
                        .clickable {
                            item.seeUrl.execute(item.seeUrl.text)
                        },
                    text = it,
                    style = MaterialTheme.typography.bodyMedium.copy(color = Color.Blue.copy(alpha = 0.4f))
                )
            }
        }
    }
}