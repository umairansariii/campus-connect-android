package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.EnrollmentDao
import com.umairansariii.campusconnect.data.local.entities.Campus
import com.umairansariii.campusconnect.data.local.entities.Department
import com.umairansariii.campusconnect.data.local.entities.Enrollment
import com.umairansariii.campusconnect.data.local.entities.University
import com.umairansariii.campusconnect.data.local.enums.UserGender
import com.umairansariii.campusconnect.domain.usecase.ValidateEmpty
import com.umairansariii.campusconnect.domain.usecase.ValidateNull
import com.umairansariii.campusconnect.presentation.events.EnrollmentFormEvent
import com.umairansariii.campusconnect.presentation.states.EnrollmentFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import java.util.Date
import javax.inject.Inject

@HiltViewModel
class EnrollmentViewModel @Inject constructor(
    private val enrollmentDao: EnrollmentDao
): ViewModel() {
    private val validateNull = ValidateNull()
    private val validateEmpty = ValidateEmpty()
    var state by mutableStateOf(EnrollmentFormState())

    private val _validationEventChannel = Channel<ValidationEvent>()
    val validationEvents = _validationEventChannel.receiveAsFlow()

    fun getUniversities(): Flow<List<University>> {
        return enrollmentDao.getUniversities()
    }

    fun getCampuses(): Flow<List<Campus>> {
        return enrollmentDao.getCampusesByUniversity(state.universityId)
    }

    fun getDepartments(): Flow<List<Department>> {
        return enrollmentDao.getDepartmentsByUniversity(state.universityId)
    }

    fun onEvent(event: EnrollmentFormEvent) {
        when(event) {
            is EnrollmentFormEvent.UniversityChanged -> {
                state = state.copy(
                    universityId = event.universityId,
                    universityIdError = null,
                    campusId = null,
                    campusIdError = null,
                    departmentId = null,
                    departmentIdError = null,
                )
            }

            is EnrollmentFormEvent.CampusChanged -> {
                state = state.copy(campusId = event.campusId, campusIdError = null)
            }

            is EnrollmentFormEvent.DepartmentChanged -> {
                state = state.copy(departmentId = event.departmentId, departmentIdError = null)
            }

            is EnrollmentFormEvent.RollNoChanged -> {
                state = state.copy(rollNo = event.rollNo, rollNoError = null)
            }

            is EnrollmentFormEvent.DobChanged -> {
                state = state.copy(dob = event.dob, dobError = null)
            }

            is EnrollmentFormEvent.GenderChanged -> {
                state = state.copy(gender = event.gender, genderError = null)
            }

            is EnrollmentFormEvent.Submit -> {
                submit(event.studentId)
            }
        }
    }

    private fun submit(studentId: Long) {
        val universityResult = validateNull.execute(value = state.universityId, fieldName = "University")
        val campusResult = validateNull.execute(value = state.campusId, fieldName = "Campus")
        val departmentResult = validateNull.execute(value = state.departmentId, fieldName = "Department")
        val rollNoResult = validateEmpty.execute(value = state.rollNo, fieldName = "Roll no")
        val dobResult = validateNull.execute(value = state.dob, fieldName = "Date of birth")
        val genderResult = validateNull.execute(value = state.gender, fieldName = "Gender")

        val hasError = listOf(
            universityResult,
            campusResult,
            departmentResult,
            rollNoResult,
            dobResult,
            genderResult,
        ).any { !it.successful }

        if (hasError) {
            state = state.copy(
                universityIdError = universityResult.errorMessage,
                campusIdError = campusResult.errorMessage,
                departmentIdError = departmentResult.errorMessage,
                rollNoError = rollNoResult.errorMessage,
                dobError = dobResult.errorMessage,
                genderError = genderResult.errorMessage,
            )
            return
        }

        viewModelScope.launch {
            enrollmentDao.insertEnrollment(
                Enrollment(
                    studentId = studentId.toInt(),
                    universityId = state.universityId?:0,
                    campusId = state.campusId?:0,
                    departmentId = state.departmentId?:0,
                    rollNo = state.rollNo,
                    dob = state.dob?: Date(),
                    gender = state.gender?:UserGender.Male,
                )
            )

            _validationEventChannel.send(ValidationEvent.Success)
        }

        state = state.copy(
            universityId = null,
            universityIdError = null,
            campusId = null,
            campusIdError = null,
            departmentId = null,
            departmentIdError = null,
            rollNo = "",
            rollNoError = null,
            gender = null,
            genderError = null,
            dob = null,
            dobError = null,
        )
    }

    sealed class ValidationEvent {
        object Success: ValidationEvent()
    }
}