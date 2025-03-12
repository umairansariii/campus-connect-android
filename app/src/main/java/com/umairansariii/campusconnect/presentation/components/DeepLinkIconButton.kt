package com.umairansariii.campusconnect.presentation.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext

@Composable
fun DeepLinkIconButton(url: String, icon: ImageVector) {
    val context = LocalContext.current

    IconButton(
        onClick = {
            openDeepLink(context, url)
        }
    ) {
        Icon(
            imageVector = icon,
            contentDescription = "deep-link-icon",
            tint = MaterialTheme.colorScheme.secondary,
        )
    }
}

private fun openDeepLink(context: Context, deepLinkUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(deepLinkUrl))

    try {
        context.startActivity(intent)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}