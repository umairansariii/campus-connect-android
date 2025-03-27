package com.umairansariii.campusconnect

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.umairansariii.campusconnect.data.local.enums.UserRole
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import com.umairansariii.campusconnect.data.store.auth.AuthState
import com.umairansariii.campusconnect.presentation.components.BottomNavigationBar
import com.umairansariii.campusconnect.presentation.components.TopNavigationBar
import com.umairansariii.campusconnect.presentation.screens.CampusScreen
import com.umairansariii.campusconnect.presentation.screens.DepartmentScreen
import com.umairansariii.campusconnect.presentation.screens.EnrollmentScreen
import com.umairansariii.campusconnect.presentation.screens.HomeScreen
import com.umairansariii.campusconnect.presentation.screens.LoadingScreen
import com.umairansariii.campusconnect.presentation.screens.LoginScreen
import com.umairansariii.campusconnect.presentation.screens.RegisterScreen
import com.umairansariii.campusconnect.presentation.screens.StudentScreen
import com.umairansariii.campusconnect.presentation.screens.UniversityDetailScreen
import com.umairansariii.campusconnect.presentation.screens.UniversityScreen
import com.umairansariii.campusconnect.viewmodel.AuthViewModel

fun NavGraphBuilder.authGraph(navController: NavHostController) {
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
}

fun NavGraphBuilder.appGraph(navController: NavHostController) {
    navigation(
        route = "app", startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(navController = navController)
        }
        composable(route = "university") {
            UniversityScreen(navController = navController)
        }
        composable(
            route = "university-detail/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            UniversityDetailScreen(universityId = universityId, navController = navController)
        }
        composable(
            route = "campus/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            CampusScreen(universityId = universityId)
        }
        composable(
            route = "department/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            DepartmentScreen(universityId = universityId)
        }
        composable(
            route = "student/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            StudentScreen(universityId = universityId)
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsStateWithLifecycle(initialValue = AuthState())

    LaunchedEffect(authState.isAuthenticated) {
        if (authState.isAuthenticated) {
            navController.navigate("app") {
                popUpTo("loading") { inclusive = true }
            }
        }

        if (
            authState.isAuthenticated &&
            authState.role == UserRole.STUDENT &&
            authState.status == UserStatus.PENDING) {
            navController.navigate("enrollment/${authState.id}") {
                popUpTo("loading") { inclusive = true }
            }
        }

        if (!authState.isAuthenticated) {
            navController.navigate("auth") {
                popUpTo("loading") { inclusive = true }
            }
        }
    }

    Scaffold(
        topBar = {
            if (shouldShowTopBar(navController = navController)) {
                TopNavigationBar(navController = navController)
            }
        },
        bottomBar = {
            if (shouldShowBottomBar(navController = navController)) {
                BottomNavigationBar()
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = "loading",
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = "loading") {
                LoadingScreen()
            }
            authGraph(navController = navController)
            appGraph(navController = navController)
        }
    }
}

@Composable
fun shouldShowBottomBar(navController: NavController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentRoute in listOf(
        "home",
        "university",
        "university-detail/{universityId}",
        "campus/{universityId}",
        "department/{universityId}",
        "student/{universityId}",
    )
}

@Composable
fun shouldShowTopBar(navController: NavController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentRoute in listOf(
        "university",
        "university-detail/{universityId}",
        "campus/{universityId}",
        "department/{universityId}",
        "student/{universityId}",
    )
}