package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.data.local.entities.User
import com.umairansariii.campusconnect.data.local.enums.UserRole
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import com.umairansariii.campusconnect.viewmodel.RegisterViewModel
import java.util.Date

@Composable
fun RegisterScreen() {
    val viewModel: RegisterViewModel = hiltViewModel()
    val users by viewModel.getAllUsers().collectAsState(initial = emptyList())

    fun buttonClicked() {
        viewModel.createUser(User(
            createdAt = Date(),
            firstName = "Google",
            lastName = "Android",
            email = "android@example.com",
            password = "HelloAndroid",
            role = UserRole.STUDENT,
            status = UserStatus.PENDING
        ))
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = "Hello Android!")
            users.forEach { user ->
                Text(text = "Name: ${user.firstName}, Email: ${user.email}")
            }
            Button(
                onClick = {
                    buttonClicked()
                }
            ) {
                Text(text = "Create User")
            }
        }
    }
}