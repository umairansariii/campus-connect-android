package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umairansariii.campusconnect.presentation.components.ClubTile
import com.umairansariii.campusconnect.presentation.components.DiscussionTile
import com.umairansariii.campusconnect.presentation.components.EventTile
import com.umairansariii.campusconnect.viewmodel.HomeViewModel

@Composable
fun HomeScreen(studentId: Int, navController: NavController) {
    val viewModel: HomeViewModel = hiltViewModel()
    val events by viewModel.getEventsByStudent(studentId).collectAsState(initial = emptyList())
    val discussions by viewModel.getDiscussionsByStudent(studentId).collectAsState(initial = emptyList())
    val clubs by viewModel.getClubsByStudent(studentId).collectAsState(initial = emptyList())

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 8.dp)
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(32.dp))
            // Upcoming Events
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Upcoming Events",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "See more",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        navController.navigate("events")
                    },
                )
            }
            LazyColumn(
                modifier = Modifier.height(154.dp)
            ) {
                items(events) { event ->
                    EventTile(event)
                }
            }
            // Popular Clubs
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Popular Clubs",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "See more",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        navController.navigate("clubs")
                    },
                )
            }
            LazyRow(
                modifier = Modifier.height(200.dp)
            ) {
                items(clubs) { club ->
                    ClubTile(club)
                }
            }
            // Trending Discussions
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp, horizontal = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "Trending Discussions",
                    style = MaterialTheme.typography.bodyLarge,
                )
                Text(
                    text = "See more",
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.clickable {
                        navController.navigate("discussions")
                    },
                )
            }
            LazyColumn(
                modifier = Modifier.height(318.dp)
            ) {
                items(discussions) { discussion ->
                    DiscussionTile(discussion)
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}