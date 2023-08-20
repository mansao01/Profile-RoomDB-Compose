package com.example.myprofile.ui.screen.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun DetailScreen(
    profileId: Int
) {
    Text(text = profileId.toString())
}