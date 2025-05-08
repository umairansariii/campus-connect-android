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
import com.umairansariii.campusconnect.presentation.components.ProfileBar
import com.umairansariii.campusconnect.presentation.components.TopNavigationBar
import com.umairansariii.campusconnect.presentation.screens.BroadcastScreen
import com.umairansariii.campusconnect.presentation.screens.CampusScreen
import com.umairansariii.campusconnect.presentation.screens.ClubAdminScreen
import com.umairansariii.campusconnect.presentation.screens.ClubChatroomScreen
import com.umairansariii.campusconnect.presentation.screens.ClubScreen
import com.umairansariii.campusconnect.presentation.screens.DepartmentScreen
import com.umairansariii.campusconnect.presentation.screens.DiscussionAdminScreen
import com.umairansariii.campusconnect.presentation.screens.DiscussionChatroomScreen
import com.umairansariii.campusconnect.presentation.screens.DiscussionScreen
import com.umairansariii.campusconnect.presentation.screens.EnrollPendingScreen
import com.umairansariii.campusconnect.presentation.screens.EnrollmentScreen
import com.umairansariii.campusconnect.presentation.screens.EventAdminScreen
import com.umairansariii.campusconnect.presentation.screens.EventScreen
import com.umairansariii.campusconnect.presentation.screens.HomeScreen
import com.umairansariii.campusconnect.presentation.screens.LoadingScreen
import com.umairansariii.campusconnect.presentation.screens.LoginScreen
import com.umairansariii.campusconnect.presentation.screens.NotificationScreen
import com.umairansariii.campusconnect.presentation.screens.RegisterScreen
import com.umairansariii.campusconnect.presentation.screens.SettingScreen
import com.umairansariii.campusconnect.presentation.screens.StudentScreen
import com.umairansariii.campusconnect.presentation.screens.UniversityDetailScreen
import com.umairansariii.campusconnect.presentation.screens.UniversityScreen
import com.umairansariii.campusconnect.viewmodel.AuthViewModel

fun NavGraphBuilder.authGraph(navController: NavHostController) {
    navigation(
        route = "auth", startDestination = "login"
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

            EnrollmentScreen(userId = userId)
        }
        composable(route = "enroll-pending") {
            EnrollPendingScreen()
        }
    }
}

fun NavGraphBuilder.appGraph(navController: NavHostController, authState: AuthState) {
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
            route = "admin/event/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            EventAdminScreen(universityId = universityId)
        }
        composable(
            route = "admin/discussion/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            DiscussionAdminScreen(universityId = universityId)
        }
        composable(
            route = "admin/club/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            ClubAdminScreen(universityId = universityId)
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
        composable(
            route = "broadcast/{universityId}",
            arguments = listOf(
                navArgument(name = "universityId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val universityId = backStackEntry.arguments?.getInt("universityId") ?: -1

            BroadcastScreen(universityId = universityId)
        }
        composable(
            route = "notification/{studentId}",
            arguments = listOf(
                navArgument(name = "studentId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val studentId = backStackEntry.arguments?.getInt("studentId") ?: -1

            NotificationScreen(studentId = studentId)
        }
        composable(route = "events") {
            EventScreen(studentId = authState.id?: -1)
        }
        composable(route = "discussions") {
            DiscussionScreen(studentId = authState.id?: -1, navController = navController)
        }
        composable(
            route = "discussion-chatroom/{discussionId}",
            arguments = listOf(
                navArgument(name = "discussionId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val discussionId = backStackEntry.arguments?.getInt("discussionId") ?: -1

            DiscussionChatroomScreen(userId = authState.id?: -1, discussionId = discussionId)
        }
        composable(route = "clubs") {
            ClubScreen(studentId = authState.id?: -1, navController = navController)
        }
        composable(
            route = "club-chatroom/{clubId}",
            arguments = listOf(
                navArgument(name = "clubId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val clubId = backStackEntry.arguments?.getInt("clubId") ?: -1

            ClubChatroomScreen(userId = authState.id?: -1, clubId = clubId)
        }
        composable(route = "settings") {
            SettingScreen(navController = navController)
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val authViewModel: AuthViewModel = hiltViewModel()
    val authState by authViewModel.authState.collectAsStateWithLifecycle(initialValue = AuthState())

    LaunchedEffect(authState.isAuthenticated, authState.status) {
        if (
            authState.isAuthenticated &&
            authState.role == UserRole.STUDENT &&
            authState.status == UserStatus.PENDING) {
            navController.navigate("enrollment/${authState.id}") {
                popUpTo("loading") { inclusive = true }
            }
        } else if (
            authState.isAuthenticated &&
            authState.role == UserRole.STUDENT &&
            authState.status == UserStatus.ENROLLED) {
            navController.navigate("enroll-pending") {
                popUpTo("loading") { inclusive = true }
            }
        } else if (
            authState.isAuthenticated &&
            authState.status == UserStatus.ACTIVE) {
            navController.navigate("app") {
                popUpTo("loading") { inclusive = true }
            }
        } else {
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
            if (shouldShowProfileBar(navController = navController)) {
                ProfileBar(navController = navController)
            }
        },
        bottomBar = {
            if (shouldShowBottomBar(navController = navController)) {
                BottomNavigationBar(navController = navController, authState = authState)
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
            appGraph(navController = navController, authState = authState)
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
        "admin/event/{universityId}",
        "admin/discussion/{universityId}",
        "admin/club/{universityId}",
        "student/{universityId}",
        "broadcast/{universityId}",
        "notification/{studentId}",
        "events",
        "discussions",
        "clubs",
        "settings",
    )
}

@Composable
fun shouldShowTopBar(navController: NavController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentRoute in listOf(
        "university-detail/{universityId}",
        "campus/{universityId}",
        "department/{universityId}",
        "admin/event/{universityId}",
        "admin/discussion/{universityId}",
        "admin/club/{universityId}",
        "student/{universityId}",
        "broadcast/{universityId}",
        "notification/{studentId}",
        "discussion-chatroom/{discussionId}",
        "club-chatroom/{clubId}",
    )
}

@Composable
fun shouldShowProfileBar(navController: NavController): Boolean {
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    return currentRoute in listOf(
        "home",
        "university",
        "events",
        "discussions",
        "clubs",
    )
}