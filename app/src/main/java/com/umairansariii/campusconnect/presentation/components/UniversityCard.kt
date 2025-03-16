package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ImportContacts
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.School
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umairansariii.campusconnect.R
import com.umairansariii.campusconnect.data.local.entities.University
import com.umairansariii.campusconnect.presentation.events.UniversityFormEvent
import com.umairansariii.campusconnect.viewmodel.UniversityViewModel

@Composable
fun UniversityCard(university: University, navController: NavController) {
    val viewModel: UniversityViewModel = hiltViewModel()

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Column(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Image(
                    painter = painterResource(id = R.drawable.profile),
                    contentDescription = "university-card-profile-icon",
                    modifier = Modifier.size(50.dp).clip(CircleShape),
                    contentScale = ContentScale.Crop,
                )
                Text(text = university.title, fontSize = 18.sp)
            }
//            Row(
//                Modifier.fillMaxWidth(),
//                horizontalArrangement = Arrangement.spacedBy(10.dp),
//                verticalAlignment = Alignment.CenterVertically,
//            ) {
//                Icon(
//                    imageVector = Icons.Outlined.School,
//                    contentDescription = "university-card-stats-icon",
//                )
//                Text(text = "4800+", fontSize = 18.sp)
//                VerticalDivider(
//                    modifier = Modifier.height(12.dp),
//                    thickness = 2.dp,
//                )
//                Icon(
//                    imageVector = Icons.Outlined.ImportContacts,
//                    contentDescription = "university-card-stats-icon",
//                )
//                Text(text = "24", fontSize = 18.sp)
//                VerticalDivider(
//                    modifier = Modifier.height(12.dp),
//                    thickness = 2.dp,
//                )
//                Icon(
//                    imageVector = Icons.Outlined.LocationOn,
//                    contentDescription = "university-card-stats-icon",
//                )
//                Text(text = "14", fontSize = 18.sp)
//            }
            Row(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.End,
            ) {
                OutlinedButton(
                    onClick = {
                        viewModel.onEvent(UniversityFormEvent.ShowDialog(id = university.id))
                    },
                ) {
                    Text(text = "Edit")
                }
                Spacer(modifier = Modifier.width(10.dp))
                Button(
                    onClick = {
                        navController.navigate("university-detail/${university.id}")
                    },
                ) {
                    Text(text = "Manage")
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}