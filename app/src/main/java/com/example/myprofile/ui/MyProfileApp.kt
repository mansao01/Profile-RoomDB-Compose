package com.example.myprofile.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.myprofile.ui.navigation.Screen
import com.example.myprofile.ui.screen.add.AddScreen
import com.example.myprofile.ui.screen.add.AddViewModel
import com.example.myprofile.ui.screen.detail.DetailScreen
import com.example.myprofile.ui.screen.detail.DetailViewModel
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
            HomeScreen(
                navigateToAdd = {
                    navController.navigate(Screen.Add.route)
                },
                navigateToDetail = { profileId ->
                    navController.navigate(Screen.Detail.createRoute(profileId))

                })
        }

        composable(Screen.Detail.route, arguments = listOf(navArgument("profileId") {
            type = NavType.IntType
        })) { data ->
            val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
            val profileId = data.arguments?.getInt("profileId") ?: -1
            DetailScreen(
                profileId = profileId,
                uiState = detailViewModel.uiState,
                moveToHome = {
                    navController.navigate(Screen.Home.route)
                }
            )
        }
        composable(Screen.Add.route) {
            val addViewModel: AddViewModel = viewModel(factory = AddViewModel.Factory)
            AddScreen(uiState = addViewModel.uiState, navigateToHome = {
                navController.navigate(Screen.Home.route)
            })
        }
    }
}