package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.umairansariii.campusconnect.presentation.components.BottomMessageBar
import com.umairansariii.campusconnect.presentation.components.ChatBubble
import com.umairansariii.campusconnect.presentation.events.ClubChatFormEvent
import com.umairansariii.campusconnect.viewmodel.ClubChatViewModel

@Composable
fun ClubChatroomScreen(userId: Int, clubId: Int) {
    val viewModel: ClubChatViewModel = hiltViewModel()
    val state = viewModel.state
    val messages by viewModel.getChatsByClub(clubId).collectAsState(initial = emptyList())

    val listState = rememberLazyListState()

    LaunchedEffect(messages.size) {
        if (messages.isNotEmpty()) {
            listState.animateScrollToItem(index = messages.lastIndex)
        }
    }

    Scaffold(
        bottomBar = {
            BottomMessageBar(
                message = state.message,
                onMessageChange = {
                    viewModel.onEvent(ClubChatFormEvent.MessageChanged(it))
                },
                onSendClick = {
                    viewModel.onEvent(ClubChatFormEvent.Submit(clubId, userId))
                },
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp),
            state = listState,
        ) {
            item {
                Spacer(modifier = Modifier.height(10.dp))
            }
            items(messages) { message ->
                ChatBubble(
                    name = "${message.firstName} ${message.lastName}",
                    text = message.message,
                    date = message.timestamp,
                    isSender = message.senderId == userId,
                )
            }
            item {
                Spacer(modifier = Modifier.height(80.dp))
            }
        }
    }
}