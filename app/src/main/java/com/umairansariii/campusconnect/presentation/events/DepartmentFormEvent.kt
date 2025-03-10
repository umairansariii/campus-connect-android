package com.umairansariii.campusconnect.presentation.events

sealed interface DepartmentFormEvent {
    data class ShowDialog(val id: Int?): DepartmentFormEvent
    class DismissDialog: DepartmentFormEvent
    data class DepartmentTitleChanged(val departmentTitle: String): DepartmentFormEvent
    data class DepartmentQueryChanged(val departmentQuery: String): DepartmentFormEvent
    data class Submit(val universityId: Int): DepartmentFormEvent
}