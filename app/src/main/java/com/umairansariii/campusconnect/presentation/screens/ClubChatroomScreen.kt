package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.presentation.components.ChatBubble
import java.util.Date

@Composable
fun ClubChatroomScreen(userId: Int) {

    Scaffold(
        bottomBar = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(color = MaterialTheme.colorScheme.surfaceContainer)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = "",
                    onValueChange = { /* Handle change */ },
                    modifier = Modifier.weight(1f),
                    placeholder = {
                        Text(text = "Type your message")
                    },
                    colors = TextFieldDefaults.colors(
                        unfocusedContainerColor = MaterialTheme.colorScheme.surfaceContainerHighest,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        disabledIndicatorColor = Color.Transparent
                    ),
                    shape = MaterialTheme.shapes.extraLarge,
                )
                Spacer(modifier = Modifier.width(8.dp))
                Box (
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .height(52.dp)
                        .width(52.dp)
                        .clip(RoundedCornerShape(50))
                        .background(color = MaterialTheme.colorScheme.primary)
                        .clickable {
                            /* Handle click */
                        },
                ) {
                    Icon(
                        Icons.AutoMirrored.Filled.Send,
                        tint = MaterialTheme.colorScheme.onPrimary,
                        contentDescription = "message-send-icon",
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(5) {
                ChatBubble(
                    name = "User Name",
                    text = "Hello! How are you doing?",
                    date = Date(),
                    isSender = true
                )
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}