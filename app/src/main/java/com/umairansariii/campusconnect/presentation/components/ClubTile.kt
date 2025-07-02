package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.data.local.dto.ClubUniversity

@Composable
fun ClubTile(club: ClubUniversity) {
    Card(
        modifier = Modifier.fillMaxWidth().clickable(
            onClick = { /* Handle click */ }
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
        ) {
            Image(
                imagePath = club.bannerUrl,
                modifier = Modifier
                    .height(130.dp)
                    .width(130.dp)
                    .clip(RoundedCornerShape(8.dp)),
            )
            Spacer(modifier = Modifier.height(10.dp))
            Text(text = club.title)
            Text(
                text = club.description,
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
    Spacer(modifier = Modifier.width(10.dp))
}