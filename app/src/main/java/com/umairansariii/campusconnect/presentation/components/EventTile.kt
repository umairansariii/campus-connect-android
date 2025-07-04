package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowOutward
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.data.local.dto.EventUniversity

@Composable
fun EventTile(event: EventUniversity) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(
            onClick = { /* Handle click */ }
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp, horizontal = 18.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Row {
                Avatar(name = event.universityName)
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text(text = event.title)
                    Text(text = event.venue)
                }
            }
            Icon(
                imageVector = Icons.Outlined.ArrowOutward,
                modifier = Modifier.size(20.dp),
                contentDescription = "redirect-event-details-icon",
            )
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}