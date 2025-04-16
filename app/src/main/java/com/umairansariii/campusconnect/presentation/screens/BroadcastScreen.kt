package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.components.NotificationCard
import com.umairansariii.campusconnect.presentation.dialogs.NotificationUpdateDialog
import com.umairansariii.campusconnect.presentation.dialogs.NotificationViewDialog
import com.umairansariii.campusconnect.presentation.events.NotificationFormEvent
import com.umairansariii.campusconnect.viewmodel.NotificationViewModel

@Composable
fun BroadcastScreen(universityId: Int) {
    val viewModel: NotificationViewModel = hiltViewModel()
    val state = viewModel.state
    val notifications by viewModel.getNotificationsByUniversity(universityId).collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(NotificationFormEvent.ShowUpdateDialog(id = null))
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "notification-add-icon")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = state.notificationQuery,
                onValueChange = {
                    viewModel.onEvent(NotificationFormEvent.NotificationQueryChanged(it))
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(Icons.Outlined.Search, contentDescription = "notification-search-icon")
                },
                shape = MaterialTheme.shapes.medium,
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(notifications) { notification ->
                    NotificationCard(notification)
                }
            }
            NotificationViewDialog()
            NotificationUpdateDialog(universityId = universityId)
        }
    }
}