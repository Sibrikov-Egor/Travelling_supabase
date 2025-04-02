package com.example.travelling.Screen

import ButtonUpLoading
import ButtonUpScreen
import TextFieldUpPassword
import TextFieldUpScreen
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.travelling.Navigation.NavigationRoutes
import com.example.travelling.R
import com.example.travelling.State.ResultDataClass
import com.example.travelling.State.SignUpScreenEvent
import com.example.travelling.State.SignUpState
import com.example.travelling.ViewModel.SignUpScreenViewModel
import com.example.travelling.ViewModel.SignUpView

@Composable
fun SIGNUP(navController: NavController, signUpView: SignUpView = viewModel()


)
{
    val timeState by signUpView.resultState.collectAsState()
    val uiDataClass = signUpView.uiDataClass
    Column(
        modifier = Modifier
            .background(color = Color(0xFFD0BCFF))
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.app_name),

            fontSize = 30.sp,
            modifier = Modifier
                .padding(top = 100.dp)

        )
        TextFieldUpScreen(
            txt = uiDataClass.email,
            label = "Email",
            onValueChange = { it -> signUpView.updateDataClass(uiDataClass.copy(email = it)) }
        )
        TextFieldUpScreen(
            txt = uiDataClass.username,
            label = "Имя",
            onValueChange = { it -> signUpView.updateDataClass(uiDataClass.copy(username = it)) }
        )
        TextFieldUpPassword(
            text = uiDataClass.password,
            label = "Пароль",
            onValueChange = { it -> signUpView.updateDataClass(uiDataClass.copy(password = it)) }
        )
        when (timeState) {
            is ResultDataClass.Error -> {
                Text(
                    (timeState as ResultDataClass.Error).message,
                    fontSize = 16.sp,
                    color = Color(0xFF7D5260),
                )
                ButtonUpScreen(text = "Зарегистрироваться") { signUpView.signUp() }
            }

            is ResultDataClass.Initialized -> {
                ButtonUpScreen(text = "Зарегистрироваться") { signUpView.signUp() }
            }

            is ResultDataClass.Loading -> {
                ButtonUpLoading(text = "Загрузка") { }
            }

            is ResultDataClass.Success -> {
                navController.navigate(NavigationRoutes.SignInScreen)
            }

            is ResultDataClass.Error -> TODO()
            ResultDataClass.Initialized -> TODO()
            ResultDataClass.Loading -> TODO()
            is ResultDataClass.Success -> TODO()
        }


        Text(
            text = stringResource(id = R.string.already_have_acc),

            modifier = Modifier
                .padding(top = 20.dp)
                .clickable {

                }

        )
        Button(
            onClick = { navController.navigate(NavigationRoutes.SignInScreen) }

        ) {
            Text(
                text = stringResource(id = R.string.login),
                fontSize = 16.sp,
            )
        }

    }

}


@Composable
fun Buttonsf(navController: NavController) {

    Button(onClick = {
        navController.navigate(NavigationRoutes.SignInScreen)
    }) {
        Text("Вернуться")
    }
}



