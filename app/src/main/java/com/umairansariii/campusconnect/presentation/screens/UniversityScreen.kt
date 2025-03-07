package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Book
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.umairansariii.campusconnect.R

@Composable
fun UniversityScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle click */ },
            ) {
                Icon(Icons.Filled.Add, "Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            Modifier.fillMaxSize().padding(start = 16.dp, top = 20.dp, end = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(6) { item ->
                UniversityCard()
            }
        }
    }
}

@Composable
fun UniversityCard() {
    Card(
        modifier = Modifier.fillMaxWidth().requiredHeight(200.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainerHigh,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "university-card-profile-icon",
                    modifier = Modifier.size(50.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Text(text = "Dawood University of Engineering and Technology", fontSize = 18.sp)
            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Icon(
                    imageVector = Icons.Outlined.School,
                    contentDescription = "university-card-stats-icon",
                )
                Text(text = "4800+", fontSize = 18.sp)
                VerticalDivider(
                    modifier = Modifier.height(12.dp),
                    thickness = 2.dp,
                )
                Icon(
                    imageVector = Icons.Outlined.Book,
                    contentDescription = "university-card-stats-icon",
                )
                Text(text = "24", fontSize = 18.sp)
                VerticalDivider(
                    modifier = Modifier.height(12.dp),
                    thickness = 2.dp,
                )
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = "university-card-stats-icon",
                )
                Text(text = "14", fontSize = 18.sp)
            }
        }
    }
}
