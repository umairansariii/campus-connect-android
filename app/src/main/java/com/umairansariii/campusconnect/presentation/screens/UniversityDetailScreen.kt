package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.umairansariii.campusconnect.R

@Composable
fun UniversityDetailScreen(universityId: Int, navController: NavController) {
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_campus),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Campuses", fontSize = 20.sp)
                        Text(text = "Manage campuses with different locations.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("campus/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_department),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Departments", fontSize = 20.sp)
                        Text(text = "Manage multiple faculty departments.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("department/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_department),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Contacts", fontSize = 20.sp)
                        Text(text = "Manage faculty and emergency contacts.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("admin/contact/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_student),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Students", fontSize = 20.sp)
                        Text(text = "Manage student academic records.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("student/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_club),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Clubs", fontSize = 20.sp)
                        Text(text = "Manage your clubs and activities.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("admin/club/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_discussion),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Discussions", fontSize = 20.sp)
                        Text(text = "Manage your groups and discussions.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("admin/discussion/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_event),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Events", fontSize = 20.sp)
                        Text(text = "Manage your upcoming events.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("admin/event/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.surfaceContainer,
                ),
            ) {
                Box {
                    Image(
                        painter = painterResource(id = R.drawable.card_notification),
                        contentDescription = "card-background-image",
                        modifier = Modifier
                            .alpha(0.2f)
                            .align(Alignment.BottomStart),
                        contentScale = ContentScale.Fit
                    )
                    Column(
                        modifier = Modifier.padding(16.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                    ) {
                        Text(text = "Notifications", fontSize = 20.sp)
                        Text(text = "Broadcast notification to students.")
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End,
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate("broadcast/$universityId")
                                }
                            ) {
                                Text(text = "Manage")
                            }
                        }
                    }
                }
            }
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}