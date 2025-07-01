package com.umairansariii.campusconnect.presentation.components

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DriveFolderUpload
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ImagePicker(onImageSelected: (Uri?) -> Unit) {
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        onImageSelected(uri)
    }

    Button(
        onClick = {
            launcher.launch("image/*")
        },
        modifier = Modifier.fillMaxWidth().height(50.dp),
    ) {
        Icon(
            imageVector = Icons.Outlined.DriveFolderUpload,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.surface,
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "Upload Image",
            style = MaterialTheme.typography.titleMedium,
        )
    }
}