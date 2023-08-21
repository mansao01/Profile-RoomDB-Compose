package com.example.myprofile.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.common.DetailUiState
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    profileId: Int,
    uiState: DetailUiState,
    moveToHome: () -> Unit,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {
    detailViewModel.getDetailProfile(profileId)
    val context = LocalContext.current
    when (uiState) {
        is DetailUiState.Loading -> Text(text = "Please wait")
        is DetailUiState.Success -> DetailComponent(
            profileFlow = uiState.profile,
            detailViewModel = detailViewModel,
            moveToHome = moveToHome
        )

        is DetailUiState.SuccessDelete -> Toast.makeText(
            context,
            uiState.message,
            Toast.LENGTH_SHORT
        ).show()

        is DetailUiState.Error -> Text(text = uiState.status)
    }
    Text(text = profileId.toString())
}

@Composable
fun DetailComponent(
    profileFlow: Flow<Profile>,
    detailViewModel: DetailViewModel,
    moveToHome: () -> Unit,

    modifier: Modifier = Modifier
) {
    val profile by profileFlow.collectAsState(initial = Profile())
    val coroutineScope = rememberCoroutineScope()
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }


    Column(modifier = Modifier) {
        Text(text = profile.name)
        Button(onClick = { deleteConfirmationRequired = true }) {
            Text(text = "Delete")
            if (deleteConfirmationRequired) {
                DeleteConfirmationDialog(
                    onDeleteConfirm = {
                        coroutineScope.launch {
                            detailViewModel.deleteProfile(profile)
                            moveToHome()
                        }
                    }, onDeleteCancel = { deleteConfirmationRequired = true })
            }
        }
    }

}

@Composable
fun DeleteConfirmationDialog(
    onDeleteConfirm: () -> Unit,
    onDeleteCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    AlertDialog(
        title = { Text(text = "Attention") },
        text = { Text(text = "Are u sure ?") },
        dismissButton = {
            TextButton(onClick = { onDeleteCancel() }) {
                Text(text = "No")

            }
        },
        onDismissRequest = {},
        confirmButton = {
            TextButton(onClick = { onDeleteConfirm() }) {
                Text(text = "Yes")
            }
        })
}