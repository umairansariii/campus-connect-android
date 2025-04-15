package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.components.NotificationTile
import com.umairansariii.campusconnect.presentation.dialogs.NotificationViewDialog
import com.umairansariii.campusconnect.viewmodel.NotificationViewModel

@Composable
fun NotificationScreen(studentId: Int) {
    val viewModel: NotificationViewModel = hiltViewModel()
    val notifications by viewModel.getNotificationsByStudent(studentId).collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(notifications) { notification ->
                NotificationTile(notification)
            }
        }
        NotificationViewDialog()
    }
}