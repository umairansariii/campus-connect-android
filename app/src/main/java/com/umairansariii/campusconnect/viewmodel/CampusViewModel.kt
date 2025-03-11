package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.CampusDao
import com.umairansariii.campusconnect.data.local.entities.Campus
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.domain.usecase.ValidateEmptyAlpha
import com.umairansariii.campusconnect.domain.usecase.ValidateEmptyInt
import com.umairansariii.campusconnect.presentation.events.CampusFormEvent
import com.umairansariii.campusconnect.presentation.states.CampusFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CampusViewModel @Inject constructor(
    private val campusDao: CampusDao,
): ViewModel() {
    private val validateEmptyAlpha = ValidateEmptyAlpha()
    private val validateEmptyInt = ValidateEmptyInt()
    private val validateEmpty = ValidateEmpty()
    var state by mutableStateOf(CampusFormState())

    fun getCampusesByUniversity(): Flow<List<Campus>> {
        return campusDao.getCampusesByUniversity(1, state.campusQuery)
    }

    fun onEvent(event: CampusFormEvent) {
        when(event) {
            is CampusFormEvent.ShowDialog -> {
                if (event.id !== null) {
                    viewModelScope.launch {
                        val campus = campusDao.getCampusById(event.id)
                        state = state.copy(
                            showDialog = true,
                            showDialogId = event.id,
                            campusTitle = campus.title,
                            campusCode = campus.campusCode,
                            campusLatitude = campus.latitude,
                            campusLongitude = campus.longitude,
                        )
                    }
                } else {
                    state = state.copy(showDialog = true)
                }
            }

            is CampusFormEvent.DismissDialog -> {
                state = state.copy(
                    showDialog = false,
                    showDialogId = null,
                    campusTitle = "",
                    campusTitleError = null,
                    campusCode = "",
                    campusCodeError = null,
                    campusLatitude = "",
                    campusLatitudeError = null,
                    campusLongitude = "",
                    campusLongitudeError = null,
                )
            }

            is CampusFormEvent.CampusTitleChanged -> {
                state = state.copy(campusTitle = event.campusTitle, campusTitleError = null)
            }

            is CampusFormEvent.CampusCodeChanged -> {
                state = state.copy(campusCode = event.campusCode, campusCodeError = null)
            }

            is CampusFormEvent.CampusLatitudeChanged -> {
                state = state.copy(campusLatitude = event.campusLatitude, campusLatitudeError = null)
            }

            is CampusFormEvent.CampusLongitudeChanged -> {
                state = state.copy(campusLongitude = event.campusLongitude, campusLongitudeError = null)
            }

            is CampusFormEvent.CampusQueryChanged -> {
                state = state.copy(campusQuery = event.campusQuery)
            }

            is CampusFormEvent.Submit -> {
                submit(event.universityId)
            }
        }
    }

    private fun submit(universityId: Int) {
        val titleResult = validateEmptyAlpha.execute(value = state.campusTitle)
        val codeResult = validateEmpty.execute(value = state.campusCode)
        val latitudeResult = validateEmptyInt.execute(value = state.campusLatitude)
        val longitudeResult = validateEmptyInt.execute(value = state.campusLongitude)

        val hasError = listOf(
            titleResult,
            codeResult,
            latitudeResult,
            longitudeResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                campusTitleError = "Title " + titleResult.errorMessage,
                campusCodeError = "Code " + codeResult.errorMessage,
                campusLatitudeError = "Latitude " + latitudeResult.errorMessage,
                campusLongitudeError = "Longitude " + longitudeResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            if (state.showDialogId !== null) {
                campusDao.updateCampus(
                    Campus(
                        id = state.showDialogId!!,
                        universityId = universityId,
                        title = state.campusTitle,
                        campusCode = state.campusCode,
                        latitude = state.campusLatitude,
                        longitude = state.campusLongitude,
                    )
                )
            } else {
                campusDao.insertCampus(
                    Campus(
                        universityId = universityId,
                        title = state.campusTitle,
                        campusCode = state.campusCode,
                        latitude = state.campusLatitude,
                        longitude = state.campusLongitude,
                    )
                )
            }
        }

        state = state.copy(
            showDialog = false,
            showDialogId = null,
            campusTitle = "",
            campusTitleError = null,
            campusCode = "",
            campusCodeError = null,
            campusLatitude = "",
            campusLatitudeError = null,
            campusLongitude = "",
            campusLongitudeError = null,
        )
    }
}