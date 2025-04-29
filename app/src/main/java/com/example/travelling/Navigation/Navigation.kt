package com.example.travelling.Navigation

import androidx.compose.material3.ListItem
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.travelling.Navigation.NavigationRoutes.SozdatelScreen
import com.example.travelling.Screen.InterfaceScreen
import com.example.travelling.Screen.SIGNUP


import com.example.travelling.Screen.SignInScreen
import com.example.travelling.Screen.SozdatelScreen
import com.example.travelling.SplashScreen.Splash


object NavigationRoutes {
    const val SignInScreen = "signin"
    const val Splash = "splash"
    const val SIGNUP = "signup"
    const val MAIN = "main"
    const val ListItem = "ListItem"
    const val  InterfaceScreen = "InterfaceScreen"
    const val  SozdatelScreen = "SozdatelScreen"

}
@Composable
fun Navigate(){
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavigationRoutes.Splash
    ) {
        composable(NavigationRoutes.Splash)
        {
            Splash(navController)
        }
        composable(NavigationRoutes.SignInScreen) {
            SignInScreen(navController)
        }
        composable(NavigationRoutes.SIGNUP)
        {
            SIGNUP(navController)
        }
        composable(NavigationRoutes.ListItem)
        {
            com.example.travelling.Item.ListItem(navController)
        }

        composable(NavigationRoutes.SozdatelScreen) {
            SozdatelScreen(navController)
        }
        composable(NavigationRoutes.InterfaceScreen + "/{id}",
            arguments = listOf(navArgument("id"){
                type = NavType.StringType
            })
        ){
            val id = it.arguments?.getString("id")

            if(id != null){
                InterfaceScreen(navController, id)
            }
        }
    }
}