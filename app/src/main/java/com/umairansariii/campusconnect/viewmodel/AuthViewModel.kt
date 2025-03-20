package com.umairansariii.campusconnect.viewmodel

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.umairansariii.campusconnect.data.local.entities.User
import com.umairansariii.campusconnect.data.store.auth.AuthState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authDataStore: DataStore<AuthState>
): ViewModel() {
    private val _authState = MutableStateFlow(AuthState())
    val authState: StateFlow<AuthState> = _authState

    init {
        viewModelScope.launch {
            authDataStore.data.collect {
                _authState.value = it
            }
        }
    }

    fun setLoggedIn(user: User) {
        viewModelScope.launch {
            authDataStore.updateData { currentState ->
                currentState.copy(
                    isAuthenticated = true,
                )
            }
        }
    }

    fun setLoggedOut() {
        viewModelScope.launch {
            authDataStore.updateData { currentState ->
                currentState.copy(
                    isAuthenticated = false,
                )
            }
        }
    }
}