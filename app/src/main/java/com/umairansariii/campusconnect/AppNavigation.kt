package com.umairansariii.campusconnect

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umairansariii.campusconnect.presentation.components.BottomNavigationBar
import com.umairansariii.campusconnect.presentation.components.TopNavigationBar
import com.umairansariii.campusconnect.presentation.screens.CampusScreen
import com.umairansariii.campusconnect.presentation.screens.EnrollmentScreen
import com.umairansariii.campusconnect.presentation.screens.LoginScreen
import com.umairansariii.campusconnect.presentation.screens.RegisterScreen
import com.umairansariii.campusconnect.presentation.screens.StudentScreen
import com.umairansariii.campusconnect.presentation.screens.UniversityScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        topBar = {
//            TopNavigationBar()
        },
        bottomBar = {
            if (shouldShowBottomBar(navController = navController)) {
                BottomNavigationBar()
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "auth",
            modifier = Modifier.padding(innerPadding)
        ) {
            navigation(
                route = "auth", startDestination = "register"
            ) {
                composable(route = "login") {
                    LoginScreen(navController = navController)
                }
                composable(route = "register") {
                    RegisterScreen(navController = navController)
                }
                composable(
                    route = "enrollment/{userId}",
                    arguments = listOf(
                        navArgument(name = "userId") { type = NavType.LongType }
                    )
                ) { backStackEntry ->
                    val userId = backStackEntry.arguments?.getLong("userId") ?: -1L

                    EnrollmentScreen(userId = userId, navController = navController)
                }
            }
            navigation(
                route = "app", startDestination = "home"
            ) {
                composable(route = "home") {
                    UniversityScreen()
                }
            }
        }
    }
}

@Composable
fun shouldShowBottomBar(navController: NavController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentRoute in listOf("home")
}