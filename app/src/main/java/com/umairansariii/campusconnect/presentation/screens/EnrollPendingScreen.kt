package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Security
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.viewmodel.AuthViewModel

@Composable
fun EnrollPendingScreen() {
    val authViewModel: AuthViewModel = hiltViewModel()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Icon(
                Icons.Outlined.Security,
                contentDescription = "enrollment-pending-approve-icon",
                tint = MaterialTheme.colorScheme.primary,
                modifier = Modifier.size(100.dp),
            )
            Spacer(modifier = Modifier.height(28.dp))
            Text(
                text = "Pending Approval",
                style = MaterialTheme.typography.headlineSmall,
            )
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                text = "Your enrollment is pending approval from the admin, please wait for the approval.",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            Spacer(modifier = Modifier.height(36.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.6f).height(50.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    authViewModel.setLoggedOut()
                },
            ) {
                Text(text = "Logout")
            }
            Spacer(modifier = Modifier.height(50.dp))
        }
    }
}