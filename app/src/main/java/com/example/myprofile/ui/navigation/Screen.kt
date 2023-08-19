package com.example.myprofile.ui.navigation

sealed class Screen(val route:String) {
    object Home:Screen("home")
    object Add:Screen("Add")
}