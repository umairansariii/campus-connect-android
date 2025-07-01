package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.umairansariii.campusconnect.R
import java.io.File

@Composable
fun Image(imagePath: String?, modifier: Modifier) {
    if (!imagePath.isNullOrEmpty()) {
        Image(
            painter = rememberAsyncImagePainter(File(imagePath)),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop,
        )
    } else {
        Image(
            painter = painterResource(id = R.drawable.image_placeholder),
            contentDescription = null,
            modifier = modifier,
            contentScale = ContentScale.Crop,
        )
    }
}