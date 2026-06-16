package com.pdm0126.rankeuca.screens.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.pdm0126.rankeuca.data.database.dao.VoteDTO
import com.pdm0126.rankeuca.model.Vote

@Composable
fun VoteCard(
    option: Vote,
    selected: Boolean,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp)
            .clickable { onClick() },
        shape = RoundedCornerShape(12.dp),
        border = if (selected) {
            BorderStroke(2.dp, Color(0xFF21A366))
        } else {
            BorderStroke(1.dp, Color.LightGray)
        }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = option.imageUrl,
                contentDescription = option.name,
                modifier = Modifier.size(64.dp)
            )

            Spacer(modifier = Modifier.width(12.dp))

            Column {
                Text(
                    text = option.name,
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = if (selected) "Tu voto" else "Toca para votar",
                    style = MaterialTheme.typography.labelSmall,
                    color = Color.Gray
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            if (selected) {
                Text(
                    text = "✓",
                    color = Color(0xFF21A366),
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}