package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.StudentDao
import com.umairansariii.campusconnect.data.local.dto.UserStudent
import com.umairansariii.campusconnect.presentation.events.StudentFormEvent
import com.umairansariii.campusconnect.presentation.states.StudentFromState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentViewModel @Inject constructor(
    private val studentDao: StudentDao
): ViewModel() {
    var state by mutableStateOf(StudentFromState())

    fun getStudentsByUniversity(universityId: Int): Flow<List<UserStudent>> {
        return studentDao.getStudentsByUniversity(universityId)
    }

    fun onEvent(event: StudentFormEvent) {
        when(event) {
            is StudentFormEvent.ShowViewDialog -> {
                viewModelScope.launch {
                    val enrolledStudent = studentDao.getEnrolledStudentById(event.id)
                    state = state.copy(
                        showViewDialog = true,
                        showViewDialogId = event.id,
                        firstName = enrolledStudent.firstName,
                        lastName = enrolledStudent.lastName,
                        email = enrolledStudent.email,
                        status = enrolledStudent.status,
                        universityName = enrolledStudent.universityName,
                        departmentName = enrolledStudent.departmentName,
                        campusName = enrolledStudent.campusName,
                        rollNo = enrolledStudent.rollNo,
                        dob = enrolledStudent.dob,
                        gender = enrolledStudent.gender,
                    )
                }
            }

            is StudentFormEvent.DismissViewDialog -> {
                state = state.copy(
                    showViewDialog = false,
                    showViewDialogId = null,
                    firstName = "",
                    lastName = "",
                    email = "",
                    status = null,
                    universityName = "",
                    departmentName = "",
                    campusName = "",
                    rollNo = "",
                    dob = null,
                    gender = null,
                )
            }

            is StudentFormEvent.DismissApproveDialog -> TODO()
            is StudentFormEvent.DismissUpdateDialog -> TODO()
            is StudentFormEvent.ShowApproveDialog -> TODO()
            is StudentFormEvent.ShowUpdateDialog -> TODO()
            is StudentFormEvent.StudentCgpaChanged -> TODO()
            is StudentFormEvent.StudentSemesterChanged -> TODO()
            is StudentFormEvent.SubmitApprove -> TODO()
            is StudentFormEvent.SubmitUpdate -> TODO()
        }
    }
}