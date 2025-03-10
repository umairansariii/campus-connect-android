package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.components.DepartmentCard
import com.umairansariii.campusconnect.presentation.dialogs.DepartmentDialog
import com.umairansariii.campusconnect.presentation.events.DepartmentFormEvent
import com.umairansariii.campusconnect.viewmodel.DepartmentViewModel

@Composable
fun DepartmentScreen() {
    val viewModel: DepartmentViewModel = hiltViewModel()
    val state = viewModel.state
    val departments by viewModel.getDepartmentsByUniversity().collectAsState(initial = emptyList())

    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = "Departments",
                style = MaterialTheme.typography.titleLarge,
            )
            IconButton(
                onClick = {
                    viewModel.onEvent(DepartmentFormEvent.ShowDialog(id = null))
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "department-add-icon")
            }
        }
        OutlinedTextField(
            value = state.departmentQuery,
            onValueChange = {
                viewModel.onEvent(DepartmentFormEvent.DepartmentQueryChanged(it))
            },
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 28.dp),
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(Icons.Outlined.Search, contentDescription = "department-search-icon")
            },
            shape = MaterialTheme.shapes.medium,
        )
        LazyColumn(
            Modifier.fillMaxWidth().padding(start = 20.dp, end = 4.dp),
        ) {
            items(departments) { department ->
                DepartmentCard(department)
            }
        }
        DepartmentDialog()
    }
}