package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.DiscussionChatDao
import com.umairansariii.campusconnect.data.local.dto.DiscussionChatUser
import com.umairansariii.campusconnect.data.local.entities.DiscussionChat
import com.umairansariii.campusconnect.presentation.events.DiscussionChatFormEvent
import com.umairansariii.campusconnect.presentation.states.DiscussionChatFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class DiscussionChatViewModel @Inject constructor(
    private val discussionChatDao: DiscussionChatDao,
): ViewModel() {
    var state by mutableStateOf(DiscussionChatFormState())

    fun getChatsByDiscussion(discussionId: Int): Flow<List<DiscussionChatUser>> {
        return discussionChatDao.getChatsByDiscussion(discussionId)
    }

    fun onEvent(event: DiscussionChatFormEvent) {
        when(event) {
            is DiscussionChatFormEvent.MessageChanged -> {
                state = state.copy(message = event.message)
            }

            is DiscussionChatFormEvent.Submit -> {
                submit(event.discussionId, event.userId)
            }
        }
    }

    private fun submit(discussionId: Int, userId: Int) {
        viewModelScope.launch {
            discussionChatDao.insertChat(
                DiscussionChat(
                    discussionId = discussionId,
                    senderId = userId,
                    message = state.message,
                    timestamp = Date(),
                )
            )
        }

        state = state.copy(message = "")
    }
}