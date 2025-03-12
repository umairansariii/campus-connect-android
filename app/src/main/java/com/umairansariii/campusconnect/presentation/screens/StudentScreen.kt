package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.presentation.components.StudentCard

@Composable
fun StudentScreen() {
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
                Icon(Icons.Outlined.Search, contentDescription = "student-search-icon")
            },
            shape = MaterialTheme.shapes.medium,
        )
        LazyColumn(
            modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(2) {
                StudentCard()
            }
        }
    }
}