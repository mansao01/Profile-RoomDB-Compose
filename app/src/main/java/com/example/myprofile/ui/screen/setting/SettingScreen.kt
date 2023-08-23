package com.example.myprofile.ui.screen.setting

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun SettingScreen(
    settingViewModel: SettingViewModel = viewModel(factory = SettingViewModel.Factory),
    onDarkModeChanged: (Boolean) -> Unit
) {
    SettingContent(settingViewModel = settingViewModel,  onDarkModeChanged = onDarkModeChanged)

}

@Composable
fun SettingContent(
    modifier: Modifier = Modifier,
    settingViewModel: SettingViewModel,
    onDarkModeChanged: (Boolean) -> Unit
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
                onDarkModeChanged(isChecked) // Notify MainActivity of the change
            },
            thumbContent = icon,
        )
    }
}