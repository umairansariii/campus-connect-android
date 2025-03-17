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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umairansariii.campusconnect.presentation.components.StudentCard
import com.umairansariii.campusconnect.presentation.dialogs.StudentApproveDialog
import com.umairansariii.campusconnect.presentation.dialogs.StudentUpdateDialog
import com.umairansariii.campusconnect.presentation.dialogs.StudentViewDialog
import com.umairansariii.campusconnect.viewmodel.StudentViewModel

@Composable
fun StudentScreen(universityId: Int, navController: NavController) {
    val viewModel: StudentViewModel = hiltViewModel()
    val students by viewModel.getStudentsByUniversity(universityId).collectAsState(initial = emptyList())

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
            items(students) { student ->
                StudentCard(student)
            }
        }
        StudentViewDialog()
        StudentUpdateDialog()
        StudentApproveDialog()
    }
}