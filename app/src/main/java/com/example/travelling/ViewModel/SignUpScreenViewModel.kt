package com.example.travelling.ViewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.travelling.State.SignUpScreenEvent
import com.example.travelling.State.SignUpState

class SignUpScreenViewModel : ViewModel() {
    var state by mutableStateOf(SignUpState())
    private set
    fun onEvent(event: SignUpScreenEvent) {
        when (event) {
            is SignUpScreenEvent.UsernameUpdated -> state = state.copy(username = event.newUsername)
            is SignUpScreenEvent.EmailUpdated -> state = state.copy(email = event.newEmail)
            is SignUpScreenEvent.PasswordUpdated -> state = state.copy(password = event.newPassword)

            }
        }
    }
