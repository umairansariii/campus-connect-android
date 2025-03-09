package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.presentation.components.DepartmentCard

@Composable
fun DepartmentScreen() {
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
                onClick = { /* Handle click */ },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "department-add-icon")
            }
        }
        OutlinedTextField(
            value = "",
            onValueChange = { /* Handle change */ },
            modifier = Modifier.fillMaxWidth().padding(start = 20.dp, end = 28.dp),
            placeholder = { Text(text = "Search") },
            leadingIcon = {
                Icon(Icons.Outlined.Search, contentDescription = "department-search-icon")
            },
        )
        LazyColumn(
            Modifier.fillMaxWidth().padding(start = 20.dp, end = 4.dp),
        ) {
            items(5) { item ->
                DepartmentCard()
            }
        }
    }
}