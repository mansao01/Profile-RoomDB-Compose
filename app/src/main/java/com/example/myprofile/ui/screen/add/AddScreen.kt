@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myprofile.ui.screen.add

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.R
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.common.AddUiState

@Composable
fun AddScreen(
    uiState: AddUiState,
    modifier: Modifier = Modifier,
    addViewModel: AddViewModel = viewModel(factory = AddViewModel.Factory),
    navigateToHome: () -> Unit
) {
    val context = LocalContext.current
    addViewModel.getUiState()
    when (uiState) {
        is AddUiState.Standby -> AddContent(
            addViewModel = addViewModel,
            modifier = modifier,
            navigateToHome = navigateToHome
        )

        is AddUiState.Success -> Toast.makeText(context, uiState.status, Toast.LENGTH_SHORT).show()
        is AddUiState.Failed -> Toast.makeText(context, uiState.status, Toast.LENGTH_SHORT).show()
    }
}

@Composable
fun AddContent(
    modifier: Modifier = Modifier,
    addViewModel: AddViewModel,
    navigateToHome: () -> Unit

) {
    Column(modifier.fillMaxSize(), verticalArrangement = Arrangement.spacedBy(16.dp)) {
        Text(
            text = "Add profile",
            color = MaterialTheme.colorScheme.primary,
            fontSize = 36.sp,
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .padding(top = 48.dp)
        )
        var name by remember {
            mutableStateOf("")
        }
        var isNameNull by remember {
            mutableStateOf(false)
        }

        var age by remember {
            mutableStateOf("")
        }
        var isAgeNull by remember {
            mutableStateOf(false)
        }

        var address by remember {
            mutableStateOf("")
        }
        var isAddressNull by remember {
            mutableStateOf(false)
        }
        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
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
                .align(Alignment.CenterHorizontally)
        )

        OutlinedTextField(
            value = age,
            onValueChange = {
                age = it
            },
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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            maxLines = 1,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        OutlinedTextField(
            value = address,
            onValueChange = {
                address = it
            },
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
                .align(Alignment.CenterHorizontally)
        )

        Button(
            onClick = {
                when {
                    name.isEmpty() -> isNameNull = true
                    age.isEmpty() -> isAgeNull = true
                    address.isEmpty() -> isAddressNull = true
                    else -> {
                        addViewModel.saveProfile(
                            Profile(
                                name = name,
                                age = age.toInt(),
                                address = address
                            )
                        )
                        navigateToHome()
                    }

                }
            },
            modifier = Modifier
                .align(Alignment.End)
                .padding(horizontal = 52.dp)
        ) {
            Text(text = "Save")
        }
    }
}

