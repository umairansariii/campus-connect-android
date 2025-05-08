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
import androidx.navigation.NavController
import com.umairansariii.campusconnect.data.local.dto.DiscussionUniversity

@Composable
fun DiscussionCard(discussion: DiscussionUniversity, navController: NavController) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp)
        ) {
            Text(
                text = discussion.title,
                style = MaterialTheme.typography.headlineSmall,
            )
            Text(text = discussion.universityName)
            Spacer(modifier = Modifier.height(18.dp))
            Text(text = discussion.description)
            Spacer(modifier = Modifier.height(10.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                Button(
                    onClick = {
                        navController.navigate("discussion-chatroom/${discussion.id}")
                    },
                ) {
                    Text(text = "Discuss")
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}