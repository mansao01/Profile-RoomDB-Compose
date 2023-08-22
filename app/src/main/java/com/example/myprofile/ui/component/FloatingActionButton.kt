package com.example.myprofile.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun SmallFAB(
    modifier: Modifier = Modifier,
    navigate: () -> Unit,
    imageVector: ImageVector
) {
    SmallFloatingActionButton(onClick = { navigate() }) {
        Icon(imageVector = imageVector, contentDescription = "add")
    }
}