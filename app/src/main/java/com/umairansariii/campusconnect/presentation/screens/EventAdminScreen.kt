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
import com.umairansariii.campusconnect.presentation.dialogs.EventDialog
import com.umairansariii.campusconnect.presentation.events.EventFormEvent
import com.umairansariii.campusconnect.viewmodel.EventViewModel

@Composable
fun EventAdminScreen(universityId: Int) {
    val viewModel: EventViewModel = hiltViewModel()
    val state = viewModel.state
    val events by viewModel.getEventsByUniversity(universityId).collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(EventFormEvent.ShowDialog(id = null))
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "event-add-icon")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle change */ },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(Icons.Outlined.Search, contentDescription = "event-search-icon")
                },
                shape = MaterialTheme.shapes.medium,
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(events) { event ->
                    //TODO: EventCard(event)
                }
            }
            EventDialog(universityId = universityId)
        }
    }
}