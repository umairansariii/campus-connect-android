package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Menu
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.umairansariii.campusconnect.data.local.enums.UserRole
import com.umairansariii.campusconnect.data.store.auth.AuthState
import com.umairansariii.campusconnect.presentation.animations.WigglingIcon
import com.umairansariii.campusconnect.viewmodel.AuthViewModel
import com.umairansariii.campusconnect.viewmodel.NotificationViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfileBar(navController: NavController) {
    val notificationViewModel: NotificationViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsStateWithLifecycle(initialValue = AuthState())
    val notifications by notificationViewModel.getNotificationsByStudent(authState.id?: -1).collectAsStateWithLifecycle(initialValue = emptyList())
    var expanded by remember { mutableStateOf(false) }

    val currentDateString = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(Date())

    val isAlert = notifications.any { notification ->
        val notificationDateString = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(notification.updatedAt)
        notificationDateString == currentDateString
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .offset(y = 40.dp)
            .padding(horizontal = 8.dp, vertical = 24.dp)
            .background(
                color = MaterialTheme.colorScheme.surfaceContainerHigh,
                shape = RoundedCornerShape(50),
            )
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 2.dp),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            IconButton(
                onClick = { /* Handle click */ },
            ) {
                Icon(
                    imageVector = Icons.Outlined.Menu,
                    contentDescription = "sidebar-menu-icon",
                )
            }
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                text = "Hello, ${authState.firstName}",
                fontSize = 18.sp,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (authState.role !== UserRole.ADMIN) {
                IconButton(
                    onClick = {
                        navController.navigate("notification/${authState.id}")
                    }
                ) {
                    if (isAlert) {
                        WigglingIcon(
                            icon = Icons.Outlined.NotificationsActive,
                            rotationRange = Pair(-10f, 10f),
                            rotationDuration = 400,
                        )
                    } else {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "notification-icon",
                        )
                    }

                }
            }
            Box {
                Avatar(
                    name = "${authState.firstName} ${authState.lastName}",
                    modifier = Modifier.clickable { expanded = !expanded },
                )
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false }
                ) {
                    DropdownMenuItem(
                        text = { Text(text = "Logout") },
                        leadingIcon = {
                            Icon(Icons.AutoMirrored.Outlined.Logout, contentDescription = "logout-icon")
                        },
                        onClick = {
                            authViewModel.setLoggedOut()
                        }
                    )
                }
            }
        }
    }
}