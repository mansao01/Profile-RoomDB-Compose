package com.example.myprofile.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun SmallFAB(
    modifier:Modifier = Modifier,
    navigateToAdd: () ->Unit
) {
    SmallFloatingActionButton(onClick = { navigateToAdd() }) {
        Icon(imageVector = Icons.Default.Add, contentDescription = "add")
    }
}