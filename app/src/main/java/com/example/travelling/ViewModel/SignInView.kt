package com.example.travelling.ViewModel

import android.provider.ContactsContract
import android.security.identity.ResultData
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.State.ResultDataClass
import com.example.travelling.State.SignInDateClass
import com.example.travelling.Supabase.Constant
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SigInView : ViewModel(){
    private val _uiDataClass = mutableStateOf(SignInDateClass())
    val uiDataClass: SignInDateClass get() = _uiDataClass.value

    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Initialized)
    val resultState: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    fun updateDataClass(newState: SignInDateClass){
        _uiDataClass.value = newState
        _resultDataClass.value = ResultDataClass.Initialized
    }

    fun signIn(){
        _resultDataClass.value = ResultDataClass.Loading
        viewModelScope.launch {
            try {
                Constant.supabase.auth.signInWith(Email){
                    email = _uiDataClass.value.email
                    password = _uiDataClass.value.password
                }
                _resultDataClass.value = ResultDataClass.Success("Ошибок нет")
            }
            catch (_ex : AuthRestException){
                _resultDataClass.value = ResultDataClass.Error("Неверный логин или пароль")
            }
        }
    }
}