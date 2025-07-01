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
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.components.ContactCard
import com.umairansariii.campusconnect.presentation.events.ContactFormEvent
import com.umairansariii.campusconnect.viewmodel.ContactViewModel

@Composable
fun ContactScreen(studentId: Int) {
    val viewModel: ContactViewModel = hiltViewModel()
    val state = viewModel.state
    val contacts by viewModel.getContactsByStudent(studentId).collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            OutlinedTextField(
                value = state.contactQuery,
                onValueChange = {
                    viewModel.onEvent(ContactFormEvent.ContactQueryChanged(it))
                },
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
                placeholder = { Text(text = "Search") },
                leadingIcon = {
                    Icon(Icons.Outlined.Search, contentDescription = "contact-search-icon")
                },
                shape = MaterialTheme.shapes.medium,
            )
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(contacts) { contact ->
                    ContactCard(contact)
                }
            }
        }
    }
}