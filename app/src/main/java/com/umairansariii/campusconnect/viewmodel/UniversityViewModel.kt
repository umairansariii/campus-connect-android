package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.UniversityDao
import com.umairansariii.campusconnect.data.local.entities.University
import com.umairansariii.campusconnect.domain.usecase.ValidateEmptyAlpha
import com.umairansariii.campusconnect.presentation.events.UniversityFormEvent
import com.umairansariii.campusconnect.presentation.states.UniversityFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UniversityViewModel @Inject constructor(
    private val universityDao: UniversityDao
): ViewModel() {
    private val validateEmptyAlpha = ValidateEmptyAlpha()
    var state by mutableStateOf(UniversityFormState())

    fun getUniversitiesByAdmin(): Flow<List<University>> {
        return universityDao.getUniversitiesByAdmin(1)
    }

    fun onEvent(event: UniversityFormEvent) {
        when(event) {
            is UniversityFormEvent.ShowDialog -> {
                if (event.id !== null) {
                    viewModelScope.launch {
                        val university = universityDao.getUniversityById(event.id)
                        state = state.copy(
                            showDialog = true,
                            showDialogId = event.id,
                            universityTitle = university.title
                        )
                    }
                } else {
                    state = state.copy(showDialog = true)
                }
            }

            is UniversityFormEvent.DismissDialog -> {
                state = state.copy(
                    showDialog = false,
                    showDialogId = null,
                    universityTitle = "",
                    universityTitleError = null
                )
            }

            is UniversityFormEvent.UniversityTitleChanged -> {
                state = state.copy(universityTitle = event.universityTitle, universityTitleError = null)
            }

            is UniversityFormEvent.Submit -> {
                submit(event.adminId)
            }
        }
    }

    private fun submit(adminId: Int) {
        val titleResult = validateEmptyAlpha.execute(value = state.universityTitle, fieldName = "Title")

        val hasError = listOf(
            titleResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                universityTitleError = titleResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            if (state.showDialogId !== null) {
                universityDao.updateUniversity(
                    University(
                        id = state.showDialogId!!,
                        adminId = adminId,
                        title = state.universityTitle,
                        avatarUrl = "",
                    )
                )
            } else {
                universityDao.insertUniversity(
                    University(
                        adminId = adminId,
                        title = state.universityTitle,
                        avatarUrl = "",
                    )
                )
            }
        }

        state = state.copy(
            showDialog = false,
            showDialogId = null,
            universityTitle = "",
            universityTitleError = null
        )
    }
}