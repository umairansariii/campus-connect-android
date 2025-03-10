package com.umairansariii.campusconnect.presentation.states

data class DepartmentFormState (
    val showDialog: Boolean = false,
    val showDialogId: Int? = null,
    val departmentTitle: String = "",
    val departmentTitleError: String? = null,
    val departmentQuery: String = "",
)