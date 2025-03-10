package com.umairansariii.campusconnect.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.dao.DepartmentDao
import com.umairansariii.campusconnect.data.local.entities.Department
import com.umairansariii.campusconnect.presentation.events.DepartmentFormEvent
import com.umairansariii.campusconnect.presentation.states.DepartmentFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DepartmentViewModel @Inject constructor(
    private val departmentDao: DepartmentDao
): ViewModel() {
    var state by mutableStateOf(DepartmentFormState())

    fun getDepartmentsByUniversity(): Flow<List<Department>> {
        return departmentDao.getDepartmentsByUniversity(1, state.departmentQuery)
    }

    fun onEvent(event: DepartmentFormEvent) {
        when(event) {
            is DepartmentFormEvent.ShowDialog -> {
                if (event.id !== null) {
                    state = state.copy(
                        showDialog = true,
                        showDialogId = event.id,
                        departmentTitle = "Untitled"
                    )
                } else {
                    state = state.copy(showDialog = true)
                }
            }

            is DepartmentFormEvent.DismissDialog -> {
                state = state.copy(
                    showDialog = false,
                    showDialogId = null,
                    departmentTitle = "",
                    departmentTitleError = null
                )
            }

            is DepartmentFormEvent.DepartmentTitleChanged -> {
                state = state.copy(departmentTitle = event.departmentTitle, departmentTitleError = null)
            }

            is DepartmentFormEvent.DepartmentQueryChanged -> {
                state = state.copy(departmentQuery = event.departmentQuery)
            }

            is DepartmentFormEvent.Create -> {
                createDepartment(event.universityId)
            }
        }
    }

    private fun createDepartment(universityId: Int) {
        viewModelScope.launch {
            departmentDao.insertDepartment(
                Department(
                    universityId = universityId,
                    title = state.departmentTitle
                )
            )
        }

        state = state.copy(showDialog = false, departmentTitle = "")
    }
}