package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.ClubChatDao
import com.umairansariii.campusconnect.data.local.dto.ClubChatUser
import com.umairansariii.campusconnect.data.local.entities.ClubChat
import com.umairansariii.campusconnect.presentation.events.ClubChatFormEvent
import com.umairansariii.campusconnect.presentation.states.ClubChatFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class ClubChatViewModel @Inject constructor(
    private val clubChatDao: ClubChatDao,
): ViewModel() {
    var state by mutableStateOf(ClubChatFormState())

    fun getChatsByClub(clubId: Int): Flow<List<ClubChatUser>> {
        return clubChatDao.getChatsByClub(clubId)
    }

    fun onEvent(event: ClubChatFormEvent) {
        when(event) {
            is ClubChatFormEvent.MessageChanged -> {
                state = state.copy(message = event.message)
            }

            is ClubChatFormEvent.Submit -> {
                submit(event.clubId, event.userId)
            }
        }
    }

    private fun submit(clubId: Int, userId: Int) {
        viewModelScope.launch {
            clubChatDao.insertChat(
                ClubChat(
                    clubId = clubId,
                    senderId = userId,
                    message = state.message,
                    timestamp = Date(),
                )
            )
        }

        state = state.copy(message = "")
    }
}