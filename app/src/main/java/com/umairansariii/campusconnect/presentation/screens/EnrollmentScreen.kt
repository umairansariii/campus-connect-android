package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umairansariii.campusconnect.data.local.enums.UserGender
import com.umairansariii.campusconnect.presentation.components.DateSelector
import com.umairansariii.campusconnect.presentation.components.SheetSelector
import com.umairansariii.campusconnect.presentation.events.EnrollmentFormEvent
import com.umairansariii.campusconnect.viewmodel.EnrollmentViewModel

@Composable
fun EnrollmentScreen(userId: Long, navController: NavController) {
    val viewModel: EnrollmentViewModel = hiltViewModel()
    val state = viewModel.state
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current

    val universities = viewModel.getUniversities().collectAsState(initial = emptyList()).value
    val campuses = viewModel.getCampuses().collectAsState(initial = emptyList()).value
    val departments = viewModel.getDepartments().collectAsState(initial = emptyList()).value
    val genders = listOf(
        UserGender.Male,
        UserGender.Female,
    )

    LaunchedEffect(key1 = context) {
        viewModel.validationEvents.collect { event ->
            when(event) {
                is EnrollmentViewModel.ValidationEvent.Success -> {
                    focusManager.clearFocus()
                    navController.navigate("app")
                }
            }
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background,
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            SheetSelector(
                label = "University",
                options = universities,
                itemToString = { it.title },
                onItemSelected = {
                    viewModel.onEvent(EnrollmentFormEvent.UniversityChanged(it.id))
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                supportingText = state.universityIdError,
                isError = state.universityIdError != null,
            )
            SheetSelector(
                label = "Campus",
                options = campuses,
                itemToString = { it.title },
                onItemSelected = {
                    viewModel.onEvent(EnrollmentFormEvent.CampusChanged(it.id))
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                supportingText = state.campusIdError,
                isError = state.campusIdError != null,
            )
            SheetSelector(
                label = "Department",
                options = departments,
                itemToString = { it.title },
                onItemSelected = {
                    viewModel.onEvent(EnrollmentFormEvent.DepartmentChanged(it.id))
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                supportingText = state.departmentIdError,
                isError = state.departmentIdError != null,
            )
            SheetSelector(
                label = "Gender",
                options = genders,
                itemToString = { it.name },
                onItemSelected = {
                    viewModel.onEvent(EnrollmentFormEvent.GenderChanged(it))
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                supportingText = state.genderError,
                isError = state.genderError != null,
            )
            OutlinedTextField(
                value = state.rollNo,
                onValueChange = {
                    viewModel.onEvent(EnrollmentFormEvent.RollNoChanged(it))
                },
                label = {
                    Text(text = "Roll No")
                },
                supportingText = {
                    if (state.rollNoError != null) {
                        Text(text = state.rollNoError)
                    }
                },
                isError = state.rollNoError != null,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                singleLine = true,
            )
            DateSelector(
                label = "Date of Birth",
                onDateSelected = {
                    viewModel.onEvent(EnrollmentFormEvent.DobChanged(it))
                },
                modifier = Modifier.fillMaxWidth(0.8f),
                supportingText = state.dobError,
                isError = state.dobError != null,
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    viewModel.onEvent(EnrollmentFormEvent.Submit(studentId = userId))
                },
            ) {
                Text(text = "Submit")
            }
        }
    }
}