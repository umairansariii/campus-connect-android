package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.StudentDao
import com.umairansariii.campusconnect.data.local.dto.UserStudent
import com.umairansariii.campusconnect.data.local.entities.Academic
import com.umairansariii.campusconnect.data.local.entities.User
import com.umairansariii.campusconnect.data.local.enums.UserStatus
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
                        cgpa = enrolledStudent.cgpa?.toString()?: "N/A",
                        semester = enrolledStudent.semester?.toString()?: "N/A",
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
                    cgpa = "",
                    semester = "",
                    dob = null,
                    gender = null,
                )
            }

            is StudentFormEvent.ShowApproveDialog -> {
                state = state.copy(
                    showApproveDialog = true,
                    showApproveDialogId = event.id,
                )
            }

            is StudentFormEvent.DismissApproveDialog -> {
                state = state.copy(
                    showApproveDialog = false,
                    showApproveDialogId = null,
                )
            }

            is StudentFormEvent.ShowUpdateDialog -> {
                viewModelScope.launch {
                    val academic = studentDao.getAcademicByStudentId(event.id)
                    state = state.copy(
                        showUpdateDialog = true,
                        showUpdateDialogId = event.id,
                        semester = academic.semester.toString(),
                        cgpa = academic.cgpa.toString(),
                    )
                }
            }

            is StudentFormEvent.DismissUpdateDialog -> {
                state = state.copy(
                    showUpdateDialog = false,
                    showUpdateDialogId = null,
                    semester = "",
                    semesterError = null,
                    cgpa = "",
                    cgpaError = null,
                )
            }

            is StudentFormEvent.StudentCgpaChanged -> {
                state = state.copy(cgpa = event.studentCgpa, cgpaError = null)
            }

            is StudentFormEvent.StudentSemesterChanged -> {
                state = state.copy(semester = event.studentSemester, semesterError = null)
            }

            is StudentFormEvent.SubmitApprove -> {
                submitApprove(event.studentId)
            }

            is StudentFormEvent.SubmitUpdate -> {
                submitUpdate(event.studentId, state.cgpa, state.semester)
            }
        }
    }

    private fun submitApprove(studentId: Int) {
        viewModelScope.launch {
            val student = studentDao.getStudentById(studentId)

            studentDao.approveStudent(
                User(
                    id = student.id,
                    createdAt = student.createdAt,
                    firstName = student.firstName,
                    lastName = student.lastName,
                    email = student.email,
                    password = student.password,
                    role = student.role,
                    status = UserStatus.ACTIVE,
                )
            )

            studentDao.insertAcademic(
                Academic(
                    enrollmentId = student.enrollmentId,
                    cgpa = 0.0,
                    semester = 0,
                )
            )
        }

        state = state.copy(
            showApproveDialog = false,
            showApproveDialogId = null,
        )
    }

    private fun submitUpdate(studentId: Int, cgpa: String, semester: String) {
        viewModelScope.launch {
            val academic = studentDao.getAcademicByStudentId(studentId)

            studentDao.updateAcademic(
                Academic(
                    id = academic.id?:0,
                    enrollmentId = academic.enrollmentId?:0,
                    semester = semester.toInt(),
                    cgpa = cgpa.toDouble(),
                )
            )
        }

        state = state.copy(
            showUpdateDialog = false,
            showUpdateDialogId = null,
            cgpa = "",
            cgpaError = null,
            semester = "",
            semesterError = null,
        )
    }
}