package com.umairansariii.campusconnect.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
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
import com.umairansariii.campusconnect.presentation.events.LoginFormEvent
import com.umairansariii.campusconnect.viewmodel.AuthViewModel
import com.umairansariii.campusconnect.viewmodel.LoginViewModel

@Composable
fun LoginScreen(navController: NavController) {
    val viewModel: LoginViewModel = hiltViewModel()
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
                is LoginViewModel.ValidationEvent.Success -> {
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
                text = "Login Account",
                modifier = Modifier.padding(vertical = 20.dp),
                style = MaterialTheme.typography.headlineMedium,
            )
            OutlinedTextField(
                value = state.email,
                onValueChange = {
                    viewModel.onEvent(LoginFormEvent.EmailChanged(it))
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
                modifier = Modifier.fillMaxWidth(0.8f).focusRequester(focusRequester),
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
                    viewModel.onEvent(LoginFormEvent.PasswordChanged(it))
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
                    imeAction = ImeAction.Done,
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        viewModel.onEvent(LoginFormEvent.Submit())
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
            Spacer(modifier = Modifier.height(8.dp))
            Button(
                modifier = Modifier.fillMaxWidth(0.8f).height(50.dp),
                shape = RoundedCornerShape(8.dp),
                onClick = {
                    viewModel.onEvent(LoginFormEvent.Submit())
                },
            ) {
                Text(text = "Login")
            }
            Spacer(modifier = Modifier.height(20.dp))
            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(0.4f)
            )
            TextButton(
                onClick = {
                    navController.navigate("register")
                },
            ) {
                Text(text = "Don't have an account? Register")
            }
        }
    }
}