package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.data.local.entities.Department
import com.umairansariii.campusconnect.presentation.events.DepartmentFormEvent
import com.umairansariii.campusconnect.viewmodel.DepartmentViewModel

@Composable
fun DepartmentCard(department: Department) {
    val viewModel: DepartmentViewModel = hiltViewModel()
    val state = viewModel.state
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = department.title)
        Box {
            IconButton(onClick = { expanded = !expanded }) {
                Icon(Icons.Filled.MoreVert, contentDescription = "department-options-icon")
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(
                    text = { Text(text = "Edit") },
                    onClick = {
                        viewModel.onEvent(DepartmentFormEvent.ShowDialog(department.id))
                    },
                )
                DropdownMenuItem(
                    text = { Text(text = "Archive") },
                    onClick = { /* Do something... */ }
                )
            }
        }
    }
}