package com.example.myprofile.ui.common

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DarkMode
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.myprofile.data.model.Profile
import kotlinx.coroutines.flow.Flow

sealed interface HomeUiState {
    data class Result(val profile: List<Profile> = listOf())
}

sealed interface AddUiState {
    object Standby : AddUiState
    data class Success(val status: String) : AddUiState
    data class Failed(val status: String) : AddUiState
}

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val profile: Flow<Profile>) : DetailUiState
    data class SuccessDelete(val message: String) : DetailUiState
    data class Error(val status: String) : DetailUiState

}

sealed interface EditUiState {
    object Loading : EditUiState
    data class Success(val profile: Profile) : EditUiState
    data class SuccessUpdate(val status: String) : EditUiState
    data class Failed(val status: String) : EditUiState
}

sealed interface SettingUiState {
    data class SettingUiState(
        val isDarkMode: Boolean = false,
        val title: String = if (isDarkMode) "Dark Mode" else "Light Mode",
        val icon: ImageVector =
            if (isDarkMode) Icons.Default.DarkMode else Icons.Default.LightMode
    )

}