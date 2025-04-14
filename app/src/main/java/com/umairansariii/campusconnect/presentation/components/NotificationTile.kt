package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.data.local.entities.Notification
import com.umairansariii.campusconnect.presentation.events.NotificationFormEvent
import com.umairansariii.campusconnect.viewmodel.NotificationViewModel
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NotificationTile(notification: Notification) {
    val viewModel: NotificationViewModel = hiltViewModel()

    Card(
        modifier = Modifier.fillMaxWidth().clickable(
            onClick = {
                viewModel.onEvent(NotificationFormEvent.ShowViewDialog(notification.id))
            }
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
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = notification.title,
                    softWrap = true,
                )
                Text(
                    text = SimpleDateFormat(
                        "dd/MM/yyyy",
                        Locale.getDefault()
                    ).format(notification.createdAt),
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
        }
    }
}