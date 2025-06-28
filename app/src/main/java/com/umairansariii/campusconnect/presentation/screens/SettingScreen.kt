package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.umairansariii.campusconnect.data.store.auth.AuthState
import com.umairansariii.campusconnect.presentation.components.Avatar
import com.umairansariii.campusconnect.presentation.components.SettingMenuItem

@Composable
fun SettingScreen(user: AuthState, navController: NavController) {

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(40.dp))
            Avatar(
                name = "${user.firstName} ${user.lastName}",
                size = 150.dp,
                fontSize = 50.sp,
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.height(44.dp),
                onClick = { /* Handle click */ }
            ) {
                Text(text = "Edit Profile")
            }
            Spacer(modifier = Modifier.height(32.dp))
            SettingMenuItem(
                title = "Student Profile",
                onClick = { navController.navigate("student-profile") }
            )
            SettingMenuItem(
                title = "Emergency Contacts",
                onClick = { navController.navigate("contacts") }
            )
            SettingMenuItem(
                title = "Privacy",
                onClick = { navController.navigate("privacy") }
            )
            SettingMenuItem(
                title = "Security",
                onClick = { navController.navigate("security") }
            )
            SettingMenuItem(
                title = "Help & Support",
                onClick = { navController.navigate("help") }
            )
        }
    }
}