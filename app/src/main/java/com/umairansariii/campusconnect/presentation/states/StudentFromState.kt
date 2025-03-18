package com.umairansariii.campusconnect.presentation.states

import com.umairansariii.campusconnect.data.local.enums.UserGender
import com.umairansariii.campusconnect.data.local.enums.UserStatus
import java.util.Date

data class StudentFromState (
    val showViewDialog: Boolean = false,
    val showViewDialogId: Int? = null,
    val showUpdateDialog: Boolean = false,
    val showUpdateDialogId: Int? = null,
    val showApproveDialog: Boolean = false,
    val showApproveDialogId: Int? = null,
    // Student Data
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val status: UserStatus? = null,
    // Enrollment Data
    val universityName: String = "",
    val departmentName: String = "",
    val campusName: String = "",
    val rollNo: String = "",
    val dob: Date? = null,
    val gender: UserGender? = null,
    // Academic Data
    val cgpa: String = "",
    val cgpaError: String? = null,
    val semester: String = "",
    val semesterError: String? = null,
)