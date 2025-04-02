package com.example.travelling.Screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.travelling.Navigation.NavigationRoutes
import com.example.travelling.R
import com.example.travelling.State.ResultDataClass
import com.example.travelling.ViewModel.SigInView

import java.time.format.TextStyle

@Composable
fun SignInScreen(navController: NavController, signInView: SigInView = viewModel()) {
    val timeState by signInView.resultState.collectAsState()
    val uiDataClass = signInView.uiDataClass
    Box(
        modifier = androidx.compose.ui.Modifier
            .background(color = Color(0xFFD0BCFF))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally){
            Text(
                text = stringResource(id = R.string.app_name),

                fontSize = 30.sp,
                modifier = androidx.compose.ui.Modifier
                    .padding(top = 100.dp)

            )
            TextFieldLogScreen(
                txtvalue = uiDataClass.email,
                label = "Email",
                onValueChange = {it -> signInView.updateDataClass(uiDataClass.copy(email = it))}
            )

            TextFieldLogScreen_Password(
                text = uiDataClass.password,
                label = "Пароль",
                onValueChange = {it -> signInView.updateDataClass(uiDataClass.copy(password = it))}
            )
            when (timeState){
                is ResultDataClass.Error -> {
                    Text((timeState as ResultDataClass.Error).message, fontSize = 16.sp, color = Color(0xFF7D5260),
                        )
                    ButtonLogScreen(
                        text = "Вход"
                    ){
                        signInView.signIn()
                    }
                }
                is ResultDataClass.Initialized -> {
                    ButtonLogScreen(
                        text = "Вход"
                    ){
                        signInView.signIn()
                    }
                }
                is ResultDataClass.Loading -> {
                    ButtonLogScreenLoading(
                        text = "Загрузка"
                    ){}
                }
                is ResultDataClass.Success -> {
                    navController.navigate(NavigationRoutes.ListItem){}
                }

                is ResultDataClass.Error -> TODO()
                ResultDataClass.Initialized -> TODO()
                ResultDataClass.Loading -> TODO()
                is ResultDataClass.Success -> TODO()
            }

            ButtonSig(navController)
        }


        }
    }



@Composable
fun ButtonSign() {
    Button(onClick = {}){
        Text("Вход")
    }
}
@Composable
fun ButtonSig(navController: NavController) {
    Text(text = "Нет аккаунта?")
    Button(onClick = {
        navController.navigate(NavigationRoutes.SIGNUP)
    }) {
        Text("Регистрация")
    }
}
