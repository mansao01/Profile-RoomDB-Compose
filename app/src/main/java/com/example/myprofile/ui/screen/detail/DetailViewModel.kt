package com.example.myprofile.ui.screen.detail

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
import com.example.myprofile.ui.common.DetailUiState
import kotlinx.coroutines.launch
import java.io.IOException

class DetailViewModel(private val repository: MyProfileRepository) : ViewModel() {
    var uiState: DetailUiState by mutableStateOf(DetailUiState.Loading)
        private set

    fun getDetailProfile(id: Int) {
        viewModelScope.launch {
            uiState = DetailUiState.Loading
            uiState = try {
                val result = repository.getProfileById(id)
                DetailUiState.Success(result)
            } catch (e: IOException) {
                DetailUiState.Error(e.toString())
            }

        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MyProfileApplication)
                val movieRepository = application.container.myProfileRepository
                DetailViewModel(repository = movieRepository)
            }
        }
    }
}