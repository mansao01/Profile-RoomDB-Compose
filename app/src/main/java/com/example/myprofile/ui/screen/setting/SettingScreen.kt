package com.example.myprofile.ui.screen.setting

import androidx.appcompat.app.AppCompatDelegate
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingScreen(
    appThemeMode: MutableState<Int>,
    settingViewModel: SettingViewModel = viewModel(factory = SettingViewModel.Factory)
) {
    SettingContent(settingViewModel,appThemeMode)

}

@Composable
fun SettingContent(
    settingViewModel: SettingViewModel,
    appThemeMode: MutableState<Int>,
    modifier: Modifier = Modifier
) {
    val uiState by settingViewModel.uiState.collectAsState()

    val icon: (@Composable () -> Unit) = {
        Icon(
            imageVector = uiState.icon,
            contentDescription = null,
            modifier = Modifier.size(SwitchDefaults.IconSize)
        )
    }

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = uiState.title)
        Switch(
            checked = uiState.isDarkMode,
            onCheckedChange = { isChecked ->
                settingViewModel.selectedTheme(isChecked)
                appThemeMode.value = if (isChecked) {
                    AppCompatDelegate.MODE_NIGHT_YES
                } else {
                    AppCompatDelegate.MODE_NIGHT_NO
                }
            },
            thumbContent = icon,
        )
    }
}