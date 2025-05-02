package com.umairansariii.campusconnect.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ChatBubble(
    name: String,
    text: String,
    date: Date,
    isSender: Boolean
) {
    val backgroundColor = if (isSender) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceVariant
    val textColor = if (isSender) MaterialTheme.colorScheme.onPrimaryContainer else MaterialTheme.colorScheme.onSurfaceVariant
    val containerArrangement = if (isSender) Arrangement.End else Arrangement.Start
    val messageAlignment = if (isSender) Alignment.End else Alignment.Start

    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 2.dp),
        horizontalArrangement = containerArrangement
    ) {
        if (!isSender) {
            Avatar(name = name)
            Spacer(modifier = Modifier.width(8.dp))
        }
        Column(
            modifier = Modifier.fillMaxWidth(0.8f),
            horizontalAlignment = messageAlignment
        ) {
            Text(
                text = text,
                modifier = Modifier
                    .background(backgroundColor, shape = RoundedCornerShape(12.dp))
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                color = textColor,
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = SimpleDateFormat("d MMM, h:mm a", Locale.getDefault()).format(date),
                modifier = Modifier.widthIn(max = 200.dp),
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                fontSize = 12.sp,
                textAlign = if (isSender) TextAlign.End else TextAlign.Start,
            )
        }
    }
}