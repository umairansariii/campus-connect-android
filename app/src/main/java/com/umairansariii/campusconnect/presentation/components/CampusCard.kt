package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.data.local.entities.Campus
import com.umairansariii.campusconnect.presentation.events.CampusFormEvent
import com.umairansariii.campusconnect.viewmodel.CampusViewModel

@Composable
fun CampusCard(campus: Campus) {
    val viewModel: CampusViewModel = hiltViewModel()
    var expanded by remember { mutableStateOf(false) }

    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surfaceContainer,
        ),
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 12.dp)
                .padding(start = 18.dp, end = 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Column {
                Text(text = campus.title)
                Text(
                    text = campus.campusCode,
                    color = MaterialTheme.colorScheme.secondary,
                    style = MaterialTheme.typography.bodyMedium,
                )
            }
            Row {
                DeepLinkIconButton(
                    url = "https://www.google.com/maps/place/${campus.latitude},${campus.longitude}",
                    icon = Icons.Outlined.LocationOn
                )
                Box {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(Icons.Filled.MoreVert, contentDescription = "campus-options-icon")
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text(text = "Edit") },
                            onClick = {
                                viewModel.onEvent(CampusFormEvent.ShowDialog(id = campus.id))
                            },
                        )
                    }
                }
            }
        }
    }
    Spacer(modifier = Modifier.height(10.dp))
}