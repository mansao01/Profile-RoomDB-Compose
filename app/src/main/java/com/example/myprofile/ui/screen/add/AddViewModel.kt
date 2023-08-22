package com.example.myprofile.ui.screen.add

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myprofile.MyProfileApplication
import com.example.myprofile.data.MyProfileRepository
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.common.AddUiState
import kotlinx.coroutines.launch
import java.io.IOException

class AddViewModel(private val repository: MyProfileRepository) : ViewModel() {

    var uiState: AddUiState by mutableStateOf(AddUiState.Standby)
        private set

    fun getUiState() {
        uiState = AddUiState.Standby
    }

    fun saveProfile(profile: Profile){
        viewModelScope.launch {
            uiState = try {
                repository.insert(profile)
                AddUiState.Success("Success")
            }catch (e: IOException) {
                AddUiState.Failed(e.message.toString())
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyProfileApplication)
                val movieRepository = application.container.myProfileRepository
                AddViewModel(repository = movieRepository)
            }
        }
    }


}