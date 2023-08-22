@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myprofile.ui.screen.detail

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.common.DetailUiState
import com.example.myprofile.ui.component.MyFAB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

@Composable
fun DetailScreen(
    profileId: Int,
    uiState: DetailUiState,
    navigateToHome: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    detailViewModel: DetailViewModel = viewModel(factory = DetailViewModel.Factory)
) {
    detailViewModel.getDetailProfile(profileId)
    val context = LocalContext.current
    when (uiState) {
        is DetailUiState.Loading -> Text(text = "Please wait")
        is DetailUiState.Success -> DetailContent(
            profileFlow = uiState.profile,
            detailViewModel = detailViewModel,
            navigateToHome = navigateToHome,
            navigateToEdit = navigateToEdit
        )

        is DetailUiState.SuccessDelete -> Toast.makeText(
            context,
            uiState.message,
            Toast.LENGTH_SHORT
        ).show()

        is DetailUiState.Error -> Text(text = uiState.status)
    }
}

@Composable
fun DetailContent(
    profileFlow: Flow<Profile>,
    detailViewModel: DetailViewModel,
    navigateToHome: () -> Unit,
    navigateToEdit: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val profile by profileFlow.collectAsState(initial = Profile())
    val coroutineScope = rememberCoroutineScope()
    var deleteConfirmationRequired by rememberSaveable { mutableStateOf(false) }

    Scaffold(
        floatingActionButton = {
            MyFAB(navigate = { navigateToEdit(profile.id) }, imageVector = Icons.Default.Create)
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    text = "Detail",
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 36.sp,
                    modifier = Modifier
                        .padding(horizontal = 12.dp)
                        .padding(top = 48.dp)
                        .align(Alignment.Start)
                )
                Card(
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer,
                        contentColor = MaterialTheme.colorScheme.onPrimaryContainer
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp)
                        .padding(top = 12.dp)

                ) {
                    Text(
                        text = profile.name,
                        style = MaterialTheme.typography.titleLarge,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .padding(top = 16.dp)
                    )
                    Text(
                        text = "Age: ${profile.age}",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                    )
                    Text(
                        text = "Address: ${profile.address}",
                        fontSize = 18.sp,
                        modifier = Modifier
                            .padding(start = 8.dp)
                            .padding(bottom = 16.dp)
                    )
                }
                Button(
                    onClick = { deleteConfirmationRequired = true },
                    modifier = modifier
                        .align(Alignment.End)
                        .padding(end = 16.dp)
                ) {
                    Text(text = "Delete")
                    if (deleteConfirmationRequired) {
                        DeleteConfirmationDialog(
                            onDeleteConfirm = {
                                coroutineScope.launch {
                                    detailViewModel.deleteProfile(profile)
                                    navigateToHome()
                                }
                            }, onDeleteCancel = { deleteConfirmationRequired = true })
                    }
                }
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
        },
        modifier = modifier
    )
}