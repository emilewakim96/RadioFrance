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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.presentation.model.ShowItem

@Composable
fun ShowCard(item: ShowItem, onClick: () -> Unit) {
    Card(
        modifier = Modifier
            .clickable {
                onClick()
            }
            .fillMaxWidth()
    ) {
        Column(modifier = Modifier.padding(8.dp)) {
            Text(
                modifier = Modifier.padding(4.dp),
                text = item.title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.SemiBold)
            )
            item.url?.let {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
            item.standFirst?.let {
                Text(
                    modifier = Modifier.padding(4.dp),
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }

            item.link.text.takeIf { it.isNotEmpty() }?.let {
                Text(
                    modifier = Modifier.padding(4.dp)
                        .clickable {
                            item.link.execute(it)
                        },
                    text = it,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}