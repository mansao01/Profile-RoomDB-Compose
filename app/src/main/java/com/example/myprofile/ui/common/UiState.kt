package com.example.myprofile.ui.common

import com.example.myprofile.data.model.Profile
import com.example.myprofile.data.model.ProfileDetail

sealed interface HomeUiState {
    data class Result(val profile: List<Profile> = listOf())
}

sealed interface AddUiState {
    object Standby : AddUiState
    data class Success(val status: String) : AddUiState
    data class Failed(val status: String) : AddUiState
}

