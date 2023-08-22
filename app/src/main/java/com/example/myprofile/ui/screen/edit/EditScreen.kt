@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myprofile.ui.screen.edit

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.ui.Alignment
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.R
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.common.EditUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect

@Composable
fun EditScreen(
    profileId: Int,
    uiState: EditUiState,
    editViewModel: EditViewModel = viewModel(factory = EditViewModel.Factory),
    navigateToHome: () -> Unit

) {
    LaunchedEffect(Unit) {
        editViewModel.getProfileById(profileId)
    }
    val context = LocalContext.current
    when (uiState) {
        is EditUiState.Loading -> Text(text = "Please wait...")
        is EditUiState.Success -> EditContent(
            profile = uiState.profile,
            navigateToHome = navigateToHome,
            editViewModel = editViewModel,
            profileId = profileId
        )

        is EditUiState.SuccessUpdate -> Toast.makeText(context, uiState.status, Toast.LENGTH_SHORT)
            .show()

        is EditUiState.Failed -> Toast.makeText(context, uiState.status, Toast.LENGTH_SHORT).show()

    }
}

@Composable
fun EditContent(
    profile: Profile,
    profileId: Int,
    editViewModel: EditViewModel,
    navigateToHome: () -> Unit
) {
    var name by remember {
        mutableStateOf(profile.name)
    }
    var isNameNull by remember {
        mutableStateOf(false)
    }
    var age by remember {
        mutableStateOf(profile.age)
    }
    var isAgeNull by remember {
        mutableStateOf(false)
    }
    var address by remember {
        mutableStateOf(profile.address)
    }
    var isAddressNull by remember {
        mutableStateOf(false)
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Edit Profile",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 48.dp)
                .align(Alignment.Start)
        )

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left
            ),
            label = {
                Text(text = stringResource(R.string.enter_your_name))
            },
            placeholder = { Text(text = stringResource(R.string.name)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.Person,
                    contentDescription = null
                )
            },
            supportingText = {
                Text(text = stringResource(R.string.require))
            },
            isError = isNameNull,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = age.toString(),
            onValueChange = { age = it.toInt() },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left
            ),
            label = {
                Text(text = stringResource(R.string.enter_your_age))
            },
            placeholder = { Text(text = stringResource(R.string.age)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.DateRange,
                    contentDescription = null
                )
            },
            supportingText = {
                Text(text = stringResource(R.string.require))
            },
            isError = isAgeNull,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            textStyle = LocalTextStyle.current.copy(
                textAlign = TextAlign.Left
            ),
            label = {
                Text(text = stringResource(R.string.enter_your_address))
            },
            placeholder = { Text(text = stringResource(R.string.address)) },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Outlined.LocationOn,
                    contentDescription = null
                )
            },
            supportingText = {
                Text(text = stringResource(R.string.require))
            },
            isError = isAddressNull,
            modifier = Modifier
                .padding(top = 16.dp)
        )

        Button(
            onClick = {
                when {
                    name.isEmpty() -> isNameNull = true
                    age.toString().isEmpty() -> isAgeNull = true
                    address.isEmpty() -> isAddressNull = true
                    else -> {
                        navigateToHome()
                        editViewModel.updateProfile(
                            Profile(
                                id= profileId,
                                name = name,
                                age = age,
                                address = address
                            )
                        )
                    }
                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(end = 16.dp)
        ) {
            Text(
                text = "Update"
            )

        }
    }
}