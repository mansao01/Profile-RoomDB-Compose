package com.example.myprofile

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import com.example.myprofile.data.AppContainer
import com.example.myprofile.data.AppDataContainer
import com.example.myprofile.data.ProfilePreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking

private const val THEME_PREFERENCES_NAME = "theme_preferences_name"
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(
    name = THEME_PREFERENCES_NAME
)

class MyProfileApplication : Application() {
    lateinit var container: AppContainer
    lateinit var profilePreferencesRepository: ProfilePreferencesRepository
    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
        profilePreferencesRepository = ProfilePreferencesRepository(dataStore)
    }
}