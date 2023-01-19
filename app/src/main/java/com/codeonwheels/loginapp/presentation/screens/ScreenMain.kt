package com.codeonwheels.loginapp.presentation.screens

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.codeonwheels.loginapp.Routes

@Composable
fun ScreenMain(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Routes.Login.route) {

        composable(Routes.Login.route) {
            LoginPage(navController = navController)
        }

        composable(Routes.Home.route) {
            Home(navController = navController)
        }

        composable(Routes.Detail.route) {
            Detail(navController = navController)
        }

        composable(Routes.AddUpdate.route) {
            AddUpdate(navController = navController)
        }
    }
}