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
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.umairansariii.campusconnect.data.local.entities.Campus
import com.umairansariii.campusconnect.data.local.entities.Department
import com.umairansariii.campusconnect.data.local.entities.University
import com.umairansariii.campusconnect.data.local.enums.UserGender
import com.umairansariii.campusconnect.presentation.components.DateSelector
import com.umairansariii.campusconnect.presentation.components.SheetSelector

@Composable
fun EnrollmentScreen() {
    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current

    val universities = listOf(
        University(
            id = 1,
            adminId = 1,
            title = "Virtual University",
            avatarUrl = ""
        ),
    )

    val campuses = listOf(
        Campus(
            id = 1,
            universityId = 1,
            title = "Nazimabad Campus",
            campusCode = "VUKHI02",
            latitude = "24.1234",
            longitude = "67.1234",
        )
    )

    val departments = listOf(
        Department(
            id = 1,
            universityId = 1,
            title = "Computer Science",
        )
    )

    val genders = listOf(
        UserGender.Male,
        UserGender.Female,
    )

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
                onItemSelected = { /* Handle item selection */ },
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            SheetSelector(
                label = "Campus",
                options = campuses,
                itemToString = { it.title },
                onItemSelected = { /* Handle item selection */ },
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            SheetSelector(
                label = "Department",
                options = departments,
                itemToString = { it.title },
                onItemSelected = { /* Handle item selection */ },
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            SheetSelector(
                label = "Gender",
                options = genders,
                itemToString = { it.name },
                onItemSelected = { /* Handle item selection */ },
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            OutlinedTextField(
                value = "",
                onValueChange = { /* Handle change */ },
                label = {
                    Text(text = "Roll No")
                },
                supportingText = { /* Handle error */ },
                isError = false,
                modifier = Modifier.fillMaxWidth(0.8f).focusRequester(focusRequester),
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
                onDateSelected = { /* Handle date selection */ },
                modifier = Modifier.fillMaxWidth(0.8f),
            )
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = { /* Handle submit */ },
            ) {
                Text(text = "Submit")
            }
        }
    }
}