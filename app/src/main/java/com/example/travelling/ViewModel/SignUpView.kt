package com.example.travelling.ViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.travelling.Item.Profile
import com.example.travelling.State.ResultDataClass
import com.example.travelling.State.SignUpState
import com.example.travelling.Supabase.Constant
import com.example.travelling.Supabase.Constant.supabase
import io.github.jan.supabase.auth.auth
import io.github.jan.supabase.auth.exception.AuthRestException
import io.github.jan.supabase.auth.providers.builtin.Email
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class SignUpView : ViewModel(){
    private val _uiDataClass = mutableStateOf(SignUpState())
    val uiDataClass: SignUpState get() = _uiDataClass.value

    private val _resultDataClass = MutableStateFlow<ResultDataClass>(ResultDataClass.Initialized)
    val resultState: StateFlow<ResultDataClass> = _resultDataClass.asStateFlow()

    fun updateDataClass(newState: SignUpState){
        _uiDataClass.value = newState
        _resultDataClass.value = ResultDataClass.Initialized
    }

    fun signUp(){
        _resultDataClass.value = ResultDataClass.Loading

        viewModelScope.launch {
                    try {
                        Constant.supabase.auth.signUpWith(Email){
                            email = _uiDataClass.value.email
                            password = _uiDataClass.value.password
                        }


                        _resultDataClass.value = ResultDataClass.Success("Нет ошибок")
                    }
                    catch (ex : AuthRestException){
                        _resultDataClass.value = ResultDataClass.Error("Ошибка получения данных")
                    } }

            }
        }







