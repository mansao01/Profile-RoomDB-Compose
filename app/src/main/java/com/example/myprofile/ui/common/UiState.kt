package com.example.myprofile.ui.common

import com.example.myprofile.data.Profile

sealed interface HomeUiState {
    data class Result(val profile: List<Profile> = listOf())
}