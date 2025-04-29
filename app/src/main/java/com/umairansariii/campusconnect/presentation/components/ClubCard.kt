package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.R
import com.umairansariii.campusconnect.data.local.dto.ClubUniversity

@Composable
fun ClubCard(club: ClubUniversity) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.image_placeholder),
                    contentDescription = "club-card-university-icon",
                    modifier = Modifier.size(50.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Column {
                    Text(text = club.universityName)
                    Text(
                        text = club.campusName,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
            Image(
                painter = painterResource(id = R.drawable.image_placeholder),
                contentDescription = "club-card-banner-image",
                modifier = Modifier.fillMaxWidth().height(200.dp),
                contentScale = ContentScale.Crop,
            )
            Column(
                modifier = Modifier.fillMaxWidth().padding(16.dp)
            ) {
                Text(text = club.title)
                Spacer(modifier = Modifier.height(4.dp))
                Text(text = club.description)
                Spacer(modifier = Modifier.height(10.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End,
                ) {
                    OutlinedButton(
                        onClick = { /* Handle click */ }
                    ) {
                        Text(text = "Details")
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                    Button(
                        onClick = { /* Handle click */ }
                    ) {
                        Text(text = "Join")
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}