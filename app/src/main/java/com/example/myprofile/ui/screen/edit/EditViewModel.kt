package com.example.myprofile.ui.screen.edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.myprofile.MyProfileApplication
import com.example.myprofile.data.MyProfileRepository
import com.example.myprofile.ui.screen.detail.DetailViewModel

class EditViewModel(private val repository: MyProfileRepository) : ViewModel() {
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