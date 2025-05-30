package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.umairansariii.campusconnect.presentation.components.VisibilityIcon
import com.umairansariii.campusconnect.presentation.events.RegisterFormEvent
import com.umairansariii.campusconnect.viewmodel.AuthViewModel
import com.umairansariii.campusconnect.viewmodel.RegisterViewModel

@Composable
fun RegisterScreen(navController: NavController) {
    val viewModel: RegisterViewModel = hiltViewModel()
    val authViewModel: AuthViewModel = hiltViewModel()
    val state = viewModel.state
    val focusRequester = remember {
        FocusRequester()
    }
    val focusManager = LocalFocusManager.current
    val context = LocalContext.current
    var passwordVisibility by remember { mutableStateOf(false) }

    LaunchedEffect(key1 = context) {
        // focusRequester.requestFocus()
        viewModel.validationEvents.collect { event ->
            when(event) {
                is RegisterViewModel.ValidationEvent.Success -> {
                    focusManager.clearFocus()
                    authViewModel.setLoggedIn(user = event.user)
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
            Text(
                text = "Register Account",
                modifier = Modifier.padding(vertical = 20.dp),
                style = MaterialTheme.typography.headlineMedium,
            )
            OutlinedTextField(
                value = state.firstName,
                onValueChange = {
                    viewModel.onEvent(RegisterFormEvent.FirstNameChanged(it))
                },
                label = {
                    Text(text = "First Name")
                },
                supportingText = {
                    if (state.firstNameError != null) {
                        Text(text = state.firstNameError)
                    }
                },
                isError = state.firstNameError != null,
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
            OutlinedTextField(
                value = state.lastName,
                onValueChange = {
                    viewModel.onEvent(RegisterFormEvent.LastNameChanged(it))
                },
                label = {
                    Text(text = "Last Name")
                },
                supportingText = {
                    if (state.lastNameError != null) {
                        Text(text = state.lastNameError)
                    }
                },
                isError = state.lastNameError != null,
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
            OutlinedTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(RegisterFormEvent.EmailChanged(it))
                },
                label = {
                    Text(text = "Email")
                },
                supportingText = {
                    if (state.emailError != null) {
                        Text(text = state.emailError)
                    }
                },
                isError = state.emailError != null,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Email,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                singleLine = true,
            )
            OutlinedTextField(
                value = state.password,
                onValueChange = {
                    viewModel.onEvent(RegisterFormEvent.PasswordChanged(it))
                },
                label = {
                    Text(text = "Password")
                },
                supportingText = {
                    if (state.passwordError != null) {
                        Text(text = state.passwordError)
                    }
                },
                isError = state.passwordError != null,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Next,
                ),
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                singleLine = true,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    VisibilityIcon(
                        state = passwordVisibility,
                        onToggle = { passwordVisibility = it }
                    )
                }
            )
            OutlinedTextField(
                value = state.repeatedPassword,
                onValueChange = {
                    viewModel.onEvent(RegisterFormEvent.RepeatedPasswordChanged(it))
                },
                label = {
                    Text(text = "Confirm Password")
                },
                supportingText = {
                    if (state.repeatedPasswordError != null) {
                        Text(text = state.repeatedPasswordError)
                    }
                },
                isError = state.repeatedPasswordError != null,
                modifier = Modifier.fillMaxWidth(0.8f),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.onEvent(RegisterFormEvent.Submit())
                    }
                ),
                singleLine = true,
                visualTransformation = if (passwordVisibility) VisualTransformation.None else PasswordVisualTransformation(),
            )
            Row(
                modifier = Modifier.fillMaxWidth(0.87f),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                Checkbox(
                    checked = state.registerAsAdmin,
                    onCheckedChange = {
                        viewModel.onEvent(RegisterFormEvent.CheckboxChanged(it))
                    }
                )
                Text(text = "Register as admin.")
            }
            Spacer(modifier = Modifier.height(12.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    viewModel.onEvent(RegisterFormEvent.Submit())
                },
            ) {
                Text(text = "Register")
            }
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            TextButton(
                onClick = {
                    navController.navigate("login")
                }
            ) {
                Text(text = "Already have an account? Login")
            }
        }
    }
}