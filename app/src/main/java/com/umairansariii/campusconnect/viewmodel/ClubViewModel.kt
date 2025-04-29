package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.ClubDao
import com.umairansariii.campusconnect.data.local.dto.ClubUniversity
import com.umairansariii.campusconnect.data.local.entities.Club
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.presentation.events.ClubFormEvent
import com.umairansariii.campusconnect.presentation.states.ClubFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClubViewModel @Inject constructor(
    private val clubDao: ClubDao,
): ViewModel() {
    private val validateEmpty = ValidateEmpty()
    var state by mutableStateOf(ClubFormState())

    fun getClubsByUniversity(universityId: Int): Flow<List<Club>> {
        return clubDao.getClubsByUniversity(universityId, state.clubQuery)
    }

    fun getClubsByStudent(studentId: Int): Flow<List<ClubUniversity>> {
        return clubDao.getClubsByStudent(studentId)
    }

    fun onEvent(event: ClubFormEvent) {
        when(event) {
            is ClubFormEvent.ShowDialog -> {
                if (event.id !== null) {
                    viewModelScope.launch {
                        val club = clubDao.getClubById(event.id)
                        state = state.copy(
                            showDialog = true,
                            showDialogId = event.id,
                            clubTitle = club.title,
                            clubDescription = club.description,
                            clubIsActive = club.isActive,
                        )
                    }
                } else {
                    state = state.copy(showDialog = true)
                }
            }

            is ClubFormEvent.DismissDialog -> {
                state = state.copy(
                    showDialog = false,
                    showDialogId = null,
                    clubTitle = "",
                    clubTitleError = null,
                    clubDescription = "",
                    clubDescriptionError = null,
                    clubIsActive = true,
                )
            }

            is ClubFormEvent.ClubTitleChanged -> {
                state = state.copy(clubTitle = event.clubTitle, clubTitleError = null)
            }

            is ClubFormEvent.ClubDescriptionChanged -> {
                state = state.copy(clubDescription = event.clubDescription, clubDescriptionError = null)
            }

            is ClubFormEvent.ClubStateChanged -> {
                state = state.copy(clubIsActive = event.clubIsActive)
            }

            is ClubFormEvent.ClubQueryChanged -> {
                state = state.copy(clubQuery = event.clubQuery)
            }

            is ClubFormEvent.Submit -> {
                submit(event.universityId)
            }
        }
    }

    private fun submit(universityId: Int) {
        val titleResult = validateEmpty.execute(value = state.clubTitle, fieldName = "Title")
        val descriptionResult = validateEmpty.execute(value = state.clubDescription, fieldName = "Description")

        val hasError = listOf(
            titleResult,
            descriptionResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                clubTitleError = titleResult.errorMessage,
                clubDescriptionError = descriptionResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            if (state.showDialogId !== null) {
                clubDao.updateClub(
                    Club(
                        id = state.showDialogId!!,
                        universityId = universityId,
                        title = state.clubTitle,
                        description = state.clubDescription,
                        isActive = state.clubIsActive,
                    )
                )
            } else {
                clubDao.insertClub(
                    Club(
                        universityId = universityId,
                        title = state.clubTitle,
                        description = state.clubDescription,
                        isActive = state.clubIsActive,
                    )
                )
            }
        }

        state = state.copy(
            showDialog = false,
            showDialogId = null,
            clubTitle = "",
            clubTitleError = null,
            clubDescription = "",
            clubDescriptionError = null,
            clubIsActive = true,
        )
    }
}