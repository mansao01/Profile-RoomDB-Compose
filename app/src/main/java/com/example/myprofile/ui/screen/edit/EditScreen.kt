@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myprofile.ui.screen.edit

import android.widget.Toast
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.common.EditUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@Composable
fun EditScreen(
    profileId: Int,
    uiState: EditUiState,
    editViewModel: EditViewModel = viewModel(factory = EditViewModel.Factory)
) {
    LaunchedEffect(Unit){
        editViewModel.getProfileById(profileId)
    }
    val context = LocalContext.current
    when (uiState) {
        is EditUiState.Loading -> Text(text = "Please wait...")
        is EditUiState.Success -> EditContent(
            nameValue = uiState.profile.name
        )
        is EditUiState.SuccessUpdate -> Toast.makeText(context, uiState.status, Toast.LENGTH_SHORT)
            .show()

        is EditUiState.Failed -> Toast.makeText(context, uiState.status, Toast.LENGTH_SHORT).show()

    }
}

@Composable
fun EditContent(
    nameValue:String
) {
    var name by remember{
        mutableStateOf(nameValue)
    }
    OutlinedTextField(
        value = name,
        onValueChange = { name = it })
}