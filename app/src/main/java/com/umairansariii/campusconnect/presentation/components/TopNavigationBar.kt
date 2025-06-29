package com.umairansariii.campusconnect.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextOverflow
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(navController: NavController) {
    val route = navController.currentBackStackEntryAsState().value?.destination?.route

    val title = when {
        route == "home" -> "Home"
        route == "university" -> "University"
        route?.startsWith("university-detail") == true -> "University Detail"
        route?.startsWith("campus") == true -> "Campus"
        route?.startsWith("department") == true -> "Department"
        route?.startsWith("admin/contact") == true -> "Contact"
        route?.startsWith("admin/event") == true -> "Event"
        route?.startsWith("admin/discussion") == true -> "Discussion"
        route?.startsWith("admin/club") == true -> "Club"
        route?.startsWith("student/") == true -> "Student"
        route?.startsWith("broadcast") == true -> "Notifications"
        route?.startsWith("notification") == true -> "Notifications"
        route?.startsWith("student-profile") == true -> "Student Profile"
        else -> "Untitled"
    }

    CenterAlignedTopAppBar(
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = title,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        },
        navigationIcon = {
            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Localized description"
                )
            }
        }
    )
}