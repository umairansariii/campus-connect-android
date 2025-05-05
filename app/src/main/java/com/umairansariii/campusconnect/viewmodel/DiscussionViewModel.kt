package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.DiscussionDao
import com.umairansariii.campusconnect.data.local.dto.DiscussionUniversity
import com.umairansariii.campusconnect.data.local.entities.Discussion
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.presentation.events.DiscussionFormEvent
import com.umairansariii.campusconnect.presentation.states.DiscussionFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiscussionViewModel @Inject constructor(
    private val discussionDao: DiscussionDao,
): ViewModel() {
    private val validateEmpty = ValidateEmpty()
    var state by mutableStateOf(DiscussionFormState())

    fun getDiscussionsByUniversity(universityId: Int): Flow<List<Discussion>> {
        return discussionDao.getDiscussionsByUniversity(universityId, state.discussionQuery)
    }

    fun getDiscussionsByStudent(studentId: Int): Flow<List<DiscussionUniversity>> {
        return discussionDao.getDiscussionsByStudent(studentId)
    }

    fun onEvent(event: DiscussionFormEvent) {
        when(event) {
            is DiscussionFormEvent.ShowDialog -> {
                if (event.id !== null) {
                    viewModelScope.launch {
                        val discussion = discussionDao.getDiscussionById(event.id)
                        state = state.copy(
                            showDialog = true,
                            showDialogId = event.id,
                            discussionTitle = discussion.title,
                            discussionDescription = discussion.description,
                            discussionIsActive = discussion.isActive,
                        )
                    }
                } else {
                    state = state.copy(showDialog = true)
                }
            }

            is DiscussionFormEvent.DismissDialog -> {
                state = state.copy(
                    showDialog = false,
                    showDialogId = null,
                    discussionTitle = "",
                    discussionTitleError = null,
                    discussionDescription = "",
                    discussionDescriptionError = null,
                    discussionIsActive = true,
                )
            }

            is DiscussionFormEvent.DiscussionTitleChanged -> {
                state = state.copy(discussionTitle = event.discussionTitle, discussionTitleError = null)
            }

            is DiscussionFormEvent.DiscussionDescriptionChanged -> {
                state = state.copy(discussionDescription = event.discussionDescription, discussionDescriptionError = null)
            }

            is DiscussionFormEvent.DiscussionStateChanged -> {
                state = state.copy(discussionIsActive = event.discussionIsActive)
            }

            is DiscussionFormEvent.DiscussionQueryChanged -> {
                state = state.copy(discussionQuery = event.discussionQuery)
            }

            is DiscussionFormEvent.Submit -> {
                submit(event.universityId)
            }
        }
    }

    private fun submit(universityId: Int) {
        val titleResult = validateEmpty.execute(value = state.discussionTitle, fieldName = "Title")
        val descriptionResult = validateEmpty.execute(value = state.discussionDescription, fieldName = "Description")

        val hasError = listOf(
            titleResult,
            descriptionResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                discussionTitleError = titleResult.errorMessage,
                discussionDescriptionError = descriptionResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            if (state.showDialogId !== null) {
                discussionDao.updateDiscussion(
                    Discussion(
                        id = state.showDialogId!!,
                        universityId = universityId,
                        title = state.discussionTitle,
                        description = state.discussionDescription,
                        isActive = state.discussionIsActive,
                    )
                )
            } else {
                discussionDao.insertDiscussion(
                    Discussion(
                        universityId = universityId,
                        title = state.discussionTitle,
                        description = state.discussionDescription,
                        isActive = state.discussionIsActive,
                    )
                )
            }
        }

        state = state.copy(
            showDialog = false,
            showDialogId = null,
            discussionTitle = "",
            discussionTitleError = null,
            discussionDescription = "",
            discussionDescriptionError = null,
            discussionIsActive = true,
        )
    }
}