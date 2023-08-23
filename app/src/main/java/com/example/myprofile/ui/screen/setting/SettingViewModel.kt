package com.example.myprofile.ui.screen.setting

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myprofile.MyProfileApplication
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

    companion object{
        val Factory:ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[APPLICATION_KEY] as MyProfileApplication
                SettingViewModel(application.profilePreferencesRepository)
            }
        }
    }
}
