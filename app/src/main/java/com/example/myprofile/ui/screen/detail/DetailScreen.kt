package com.example.myprofile.ui.screen.detail

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.common.DetailUiState
import kotlinx.coroutines.flow.Flow

@Composable
fun DetailScreen(
    profileId: Int,
    uiState: DetailUiState,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {
    detailViewModel.getDetailProfile(profileId)
    when (uiState) {
        is DetailUiState.Loading -> Text(text = "Please wait")
        is DetailUiState.Success -> DetailComponent(
            profileFlow = uiState.profile
        )

        is DetailUiState.Error -> Text(text = uiState.status)
    }
    Text(text = profileId.toString())
}

@Composable
fun DetailComponent(profileFlow: Flow<Profile>, modifier: Modifier = Modifier) {
    val profile by profileFlow.collectAsState(initial = Profile())
    Text(text = profile.name)
}