package com.umairansariii.campusconnect.presentation.screens

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StudentProfileScreen(studentId: Int) {
    val tabTitles = listOf("Student Information", "Academic Information")
    var selectedTabIndex by remember { mutableIntStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Hello, $studentId")
            TabRow(
                selectedTabIndex = selectedTabIndex,
                modifier = Modifier.fillMaxWidth()
            ) {
                tabTitles.forEachIndexed { index, title ->
                    Tab(
                        selected = selectedTabIndex == index,
                        onClick = { selectedTabIndex = index },
                        text = { Text(title) }
                    )
                }
            }

            // Content based on selected tab
            when (selectedTabIndex) {
                0 -> StudentInformationContent()
                1 -> AcademicInformationContent()
            }
        }
    }
}

@Composable
fun StudentInformationContent() {
    Column(modifier = Modifier.padding(horizontal = 8.dp, vertical = 12.dp)) {
        OutlinedCard(
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface,
            ),
            border = BorderStroke(1.dp, MaterialTheme.colorScheme.secondary),
            modifier = Modifier.fillMaxWidth()
        ) {
            Column(
                modifier = Modifier.fillMaxWidth().padding(12.dp)
            ) {
                Row {
                    Text(text = "Name: ")
                    Text(
                        text = "Muhammad Umair",
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
                Row {
                    Text(text = "Email: ")
                    Text(
                        text = "umairansari.work@gmail.com",
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
                Row {
                    Text(text = "Enrollment: ")
                    Text(
                        text = "BC210402929",
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
                Row {
                    Text(text = "Birthday: ")
                    Text(
                        text = "02/06/2000",
                        color = MaterialTheme.colorScheme.secondary,
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        Row {
            Text(text = "Department: ")
            Text(
                text = "Computer Science",
                color = MaterialTheme.colorScheme.secondary,
            )
        }
        Row {
            Text(text = "Campus: ")
            Text(
                text = "Nazimabad Campus",
                color = MaterialTheme.colorScheme.secondary,
            )
        }

    }
}

@Composable
fun AcademicInformationContent() {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(text = "This is the content for Academic Information.")
        // Add your specific UI for academic information here
        Text(text = "Major: Computer Science")
        Text(text = "GPA: 3.8")
        Text(text = "Current Courses: Data Structures, Algorithms, Mobile Development")
    }
}