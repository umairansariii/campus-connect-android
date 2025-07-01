package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.data.local.dto.EventUniversity

@Composable
fun EventCard(event: EventUniversity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column {
//            Row(
//                Modifier
//                    .fillMaxWidth()
//                    .padding(vertical = 12.dp, horizontal = 16.dp),
//                horizontalArrangement = Arrangement.spacedBy(16.dp),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
//                Image(
//                    painter = painterResource(id = R.drawable.image_placeholder),
//                    contentDescription = "event-card-university-icon",
//                    modifier = Modifier.size(50.dp).clip(CircleShape),
//                    contentScale = ContentScale.Crop,
//                )
//                Column {
//                    Text(text = event.universityName)
//                    Text(
//                        text = event.campusName,
//                        style = MaterialTheme.typography.bodyMedium,
//                    )
//                }
//            }
            Image(
                imagePath = event.bannerUrl,
                modifier = Modifier.fillMaxWidth().height(220.dp),
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(text = event.title)
                Text(text = event.venue)
                Spacer(modifier = Modifier.height(16.dp))
                Text(text = event.description)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    Button(
                        onClick = { /* Handle click */ }
                    ) {
                        Text(text = "View Details")
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}