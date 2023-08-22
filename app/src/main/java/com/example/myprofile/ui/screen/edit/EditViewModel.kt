package com.example.myprofile.ui.screen.edit

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
import com.example.myprofile.ui.common.EditUiState
import com.example.myprofile.ui.screen.detail.DetailViewModel
import kotlinx.coroutines.launch
import java.io.IOException

class EditViewModel(private val repository: MyProfileRepository) : ViewModel() {

    var uiState: EditUiState by mutableStateOf(EditUiState.Loading)
        private set

    fun getProfileById(id: Int) {
        viewModelScope.launch {
            uiState = EditUiState.Loading
            uiState = try {
                val result = repository.getProfileByIdForEdit(id)
                EditUiState.Success(result)
            } catch (e: IOException) {
                EditUiState.Failed("Failed to get data")
            }
        }
    }

    fun updateProfile(profile: Profile) {
        viewModelScope.launch {
            uiState = try {
                repository.insert(profile)
                EditUiState.SuccessUpdate("Success")
            } catch (e: IOException) {
                EditUiState.Failed(e.message.toString())
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyProfileApplication)
                val movieRepository = application.container.myProfileRepository
                EditViewModel(repository = movieRepository)
            }
        }
    }
}