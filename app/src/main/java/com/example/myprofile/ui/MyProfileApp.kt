package com.example.myprofile.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myprofile.ui.navigation.Screen
import com.example.myprofile.ui.screen.add.AddScreen
import com.example.myprofile.ui.screen.home.HomeScreen

@Composable
fun MyProfileApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navigateToAdd = {
                navController.navigate(Screen.Add.route)
            })
        }
        composable(Screen.Add.route) {
            AddScreen()
        }
    }
}