package com.example.myprofile.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myprofile.data.ProfilePreferencesRepository
import com.example.myprofile.ui.common.SettingUiState
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SettingViewModel(private val profilePreferencesRepository: ProfilePreferencesRepository) :
    ViewModel() {
    val uiState: StateFlow<SettingUiState.SettingUiState> =
        profilePreferencesRepository.isDarkMode.map { isDarkMode ->
            SettingUiState.SettingUiState(isDarkMode)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(),
            initialValue = SettingUiState.SettingUiState()
        )

    fun selectedTheme(isDarkMode: Boolean) {
        viewModelScope.launch {
            profilePreferencesRepository.saveThemePreferences(isDarkMode)
        }
    }
}
