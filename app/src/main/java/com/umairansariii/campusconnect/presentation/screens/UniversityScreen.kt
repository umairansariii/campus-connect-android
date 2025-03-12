package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.presentation.components.UniversityCard

@Composable
fun UniversityScreen() {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /* Handle click */ },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "university-add-icon")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(2) { item ->
                    UniversityCard()
                }
            }
        }
    }
}