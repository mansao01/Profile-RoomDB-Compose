package com.example.myprofile.ui.common

import com.example.myprofile.data.model.Profile
import kotlinx.coroutines.flow.Flow

sealed interface HomeUiState {
    data class Result(val profile: List<Profile> = listOf())
}

sealed interface AddUiState {
    object Standby : AddUiState
    data class Success(val status: String) : AddUiState
    data class Failed(val status: String) : AddUiState
}

sealed interface DetailUiState {
    object Loading : DetailUiState
    data class Success(val profile: Flow<Profile>) : DetailUiState
    data class SuccessDelete(val message: String) : DetailUiState
    data class Error(val status: String) : DetailUiState

}

sealed interface EditUiState {
    object Loading : EditUiState
    data class Success(val profile: Flow<Profile>) : EditUiState
    data class SuccessUpdate(val status: String) : EditUiState
    data class Failed(val status: String) : EditUiState
}
