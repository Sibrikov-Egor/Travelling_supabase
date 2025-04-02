package com.example.travelling.SplashScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.travelling.Navigation.NavigationRoutes
import com.example.travelling.R
import com.example.travelling.R.drawable.android
import kotlinx.coroutines.delay
import java.lang.reflect.Modifier

@Composable
fun Splash(navController: NavController)
{
    Column(modifier = androidx.compose.ui.Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center)
    {
        Image(painter = painterResource(id = R.drawable.logo), contentDescription = "android,",
                modifier = androidx.compose.ui.Modifier.width(400.dp).height(380.dp))
        LinearProgressIndicator(color = Color(0xFFD0BCFF))
    }
    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate(NavigationRoutes.SignInScreen) {
            popUpTo(NavigationRoutes.Splash) {
                inclusive = true
            }
        }
    }
}