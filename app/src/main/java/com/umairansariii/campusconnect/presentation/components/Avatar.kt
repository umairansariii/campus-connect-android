package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Avatar(
    name: String,
    size: Dp = 44.dp,
    fontSize: TextUnit = 16.sp,
) {
    val initials = name.split(" ")
        .take(n = 2)
        .mapNotNull { it.firstOrNull()?.toString()?.uppercase() }
        .joinToString(separator = "")

    Box(
        modifier = Modifier
            .size(size)
            .background(color = MaterialTheme.colorScheme.primary, shape = CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = initials,
            fontSize = fontSize,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onPrimary,
        )
    }
}