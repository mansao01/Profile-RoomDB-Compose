package com.example.myprofile

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.myprofile.ui.MyProfileApp
import com.example.myprofile.ui.theme.MyProfileTheme
import kotlinx.coroutines.flow.catch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val application = application as MyProfileApplication
        val profilePreferencesRepository = application.profilePreferencesRepository

        super.onCreate(savedInstanceState)
        setContent {

            val isDarkMode = remember { mutableStateOf(false) }

            // Collect the isDarkMode state from DataStore
            profilePreferencesRepository.isDarkMode
                .collectAsState(initial = false)
                .value
                .also { collectedState ->
                    isDarkMode.value = collectedState
                }

            MyProfileTheme(darkTheme = isDarkMode.value) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MyProfileApp(
                        onDarkModeChanged = { newDarkMode ->
                            isDarkMode.value = newDarkMode
                        })
                }
            }
        }
    }
}
