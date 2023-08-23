package com.example.myprofile.ui

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
import com.example.myprofile.ui.screen.edit.EditScreen
import com.example.myprofile.ui.screen.edit.EditViewModel
import com.example.myprofile.ui.screen.home.HomeScreen
import com.example.myprofile.ui.screen.setting.SettingScreen
import com.example.myprofile.ui.screen.setting.SettingViewModel

@Composable
fun MyProfileApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    onDarkModeChanged: (Boolean) -> Unit
) {
    val appThemeMode = remember{ mutableStateOf(AppCompatDelegate.MODE_NIGHT_NO) }
    NavHost(
        navController = navController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToAdd = {
                    navController.navigate(Screen.Add.route)
                },
                navigateToDetail = { profileId ->
                    navController.navigate(Screen.Detail.createRoute(profileId))

                },
                navigateToSetting = {
                    navController.navigate(Screen.Setting.route)
                },
            )
        }

        composable(Screen.Setting.route) {
            val settingViewModel: SettingViewModel = viewModel(factory = SettingViewModel.Factory)
            SettingScreen(
                settingViewModel = settingViewModel,
                onDarkModeChanged = onDarkModeChanged
            )
        }
        composable(Screen.Detail.route, arguments = listOf(navArgument("profileId") {
            type = NavType.IntType
        })) { data ->
            val detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
            val profileId = data.arguments?.getInt("profileId") ?: -1
            DetailScreen(
                profileId = profileId,
                uiState = detailViewModel.uiState,
                navigateToHome = {
                    navController.navigate(Screen.Home.route)
                },
                navigateToEdit = { id ->
                    navController.navigate(Screen.Edit.createRoute(id))
                }
            )
        }

        composable(Screen.Edit.route, arguments = listOf(navArgument("profileId") {
            type = NavType.IntType
        })) { data ->
            val profileId = data.arguments?.getInt("profileId") ?: -1
            val editViewModel: EditViewModel = viewModel(factory = EditViewModel.Factory)
            EditScreen(
                profileId = profileId,
                uiState = editViewModel.uiState,
                navigateToHome = {
                    navController.navigate(Screen.Home.route)
                })
        }
        composable(Screen.Add.route) {
            val addViewModel: AddViewModel = viewModel(factory = AddViewModel.Factory)
            AddScreen(uiState = addViewModel.uiState, navigateToHome = {
                navController.navigate(Screen.Home.route)
            })
        }
    }
}