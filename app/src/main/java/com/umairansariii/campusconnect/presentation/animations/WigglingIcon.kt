package com.umairansariii.campusconnect.presentation.animations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun WigglingIcon(
    icon: ImageVector,
    rotationRange: Pair<Float, Float> = Pair(-5f, 5f),
    rotationDuration: Int = 500,
) {
    val wiggleState = rememberInfiniteTransition()

    val rotation by wiggleState.animateFloat(
        initialValue = rotationRange.first,
        targetValue = rotationRange.second,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = rotationDuration, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    Icon(
        imageVector = icon,
        contentDescription = "wiggling-icon",
        modifier = Modifier.rotate(rotation),
    )
}