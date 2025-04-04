package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Visibility
import androidx.compose.material.icons.outlined.VisibilityOff
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics

@Composable
fun VisibilityIcon(state: Boolean, onToggle: (Boolean) -> Unit) {
    val imageVector: ImageVector = if (state) {
        Icons.Outlined.Visibility
    } else {
        Icons.Outlined.VisibilityOff
    }

    Icon(
        imageVector = imageVector,
        contentDescription = "password-visibility-icon",
        modifier = Modifier
            .clickable { onToggle(!state) }
            .semantics {
                role = Role.Checkbox
                this.contentDescription = "password-visibility-icon"
            }
    )
}