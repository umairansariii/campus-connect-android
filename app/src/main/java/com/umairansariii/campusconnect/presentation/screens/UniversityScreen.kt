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
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.umairansariii.campusconnect.data.store.auth.AuthState
import com.umairansariii.campusconnect.presentation.components.UniversityCard
import com.umairansariii.campusconnect.presentation.dialogs.UniversityDialog
import com.umairansariii.campusconnect.presentation.events.UniversityFormEvent
import com.umairansariii.campusconnect.viewmodel.AuthViewModel
import com.umairansariii.campusconnect.viewmodel.UniversityViewModel

@Composable
fun UniversityScreen(navController: NavController) {
    val viewModel: UniversityViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsStateWithLifecycle(initialValue = AuthState())
    val universities by viewModel.getUniversitiesByAdmin(authState.id).collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    viewModel.onEvent(UniversityFormEvent.ShowDialog(id = null))
                },
            ) {
                Icon(Icons.Filled.Add, contentDescription = "university-add-icon")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.fillMaxSize(),
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            LazyColumn(
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp),
            ) {
                item {
                    Spacer(modifier = Modifier.height(10.dp))
                }
                items(universities) { university ->
                    UniversityCard(university, navController)
                }
            }
            UniversityDialog(authState.id)
        }
    }
}