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
    // Enrolled Student Data
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val status: UserStatus? = null,
    val universityName: String = "",
    val departmentName: String = "",
    val campusName: String = "",
    val rollNo: String = "",
    val dob: Date? = null,
    val gender: UserGender? = null,
    // Academic Student Data
    val cgpa: Double = 0.0,
    val cgpaError: String? = null,
    val semester: Int? = 1,
    val semesterError: String? = null,
)