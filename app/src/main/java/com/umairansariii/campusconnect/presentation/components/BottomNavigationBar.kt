package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Event
import androidx.compose.material.icons.filled.Group
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.SportsBaseball
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Group
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material.icons.outlined.SportsBaseball
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.umairansariii.campusconnect.presentation.states.BottomNavigationItem

val items = listOf(
    BottomNavigationItem(
        title = "Home",
        route = "home",
        selectedIcon = Icons.Filled.Home,
        unselectedIcon = Icons.Outlined.Home,
        hasNews = false,
        badgeCount = 0,
    ),
    BottomNavigationItem(
        title = "Events",
        route = "events",
        selectedIcon = Icons.Filled.Event,
        unselectedIcon = Icons.Outlined.Event,
        hasNews = false,
        badgeCount = 3,
    ),
    BottomNavigationItem(
        title = "Groups",
        route = "groups",
        selectedIcon = Icons.Filled.Group,
        unselectedIcon = Icons.Outlined.Group,
        hasNews = false,
        badgeCount = 0
    ),
    BottomNavigationItem(
        title = "Clubs",
        route = "clubs",
        selectedIcon = Icons.Filled.SportsBaseball,
        unselectedIcon = Icons.Outlined.SportsBaseball,
        hasNews = false,
        badgeCount = 0
    ),
    BottomNavigationItem(
        title = "Settings",
        route = "settings",
        selectedIcon = Icons.Filled.Settings,
        unselectedIcon = Icons.Outlined.Settings,
        hasNews = true,
        badgeCount = 0
    ),
)

@Composable
fun BottomNavigationBar() {
    var selected by remember {
        mutableIntStateOf(0)
    }

    NavigationBar {
        Row(
            modifier = Modifier.background(MaterialTheme.colorScheme.inverseOnSurface)
        ) {
            items.forEachIndexed { index, item ->
                NavigationBarItem(
                    selected = index == selected,
                    onClick = {
                        selected = index
                    },
                    icon = {
                        BadgedBox(
                            badge = {
                                if (item.badgeCount != 0) {
                                    Badge {
                                        Text(text = item.badgeCount.toString())
                                    }
                                } else if (item.hasNews) {
                                    Badge()
                                }
                            }
                        ) {
                            Icon(
                                imageVector =
                                if (index == selected) {
                                    item.selectedIcon
                                } else {
                                    item.unselectedIcon
                                },
                                contentDescription = item.title,
                                tint = MaterialTheme.colorScheme.onBackground,
                            )
                        }
                    },
                    label = {
                        Text(text = item.title)
                    }
                )
            }
        }
    }
}