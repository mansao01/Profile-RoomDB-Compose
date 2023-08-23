package com.example.myprofile.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Detail : Screen("home/{profileId}") {
        fun createRoute(profileId: Int) = "home/$profileId"
    }

    object Edit : Screen("detail/{profileId}") {
        fun createRoute(profileId: Int) = "detail/$profileId"
    }

    object Setting:Screen("setting")

    object Add : Screen("Add")
}