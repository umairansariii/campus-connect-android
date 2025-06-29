package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.StudentDao
import com.umairansariii.campusconnect.presentation.states.StudentProfileState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StudentProfileViewModel @Inject constructor(
    private val studentDao: StudentDao,
    savedStateHandle: SavedStateHandle,
): ViewModel() {
    var state by mutableStateOf(StudentProfileState())

    init {
        val studentId = savedStateHandle.get<Int>("studentId") ?: -1
        loadStudentData(studentId)
    }

    private fun loadStudentData(studentId: Int) {
        viewModelScope.launch {
            val enrolledStudent = studentDao.getEnrolledStudentById(studentId)
            state = state.copy(
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
                cgpa = enrolledStudent.cgpa?.toString()?: "N/A",
                semester = enrolledStudent.semester?.toString()?: "N/A",
            )
        }
    }
}