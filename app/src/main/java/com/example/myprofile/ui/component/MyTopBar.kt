package com.example.myprofile.ui.component

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.myprofile.R

@ExperimentalMaterial3Api
@Composable
fun MyTopBar(
    scrollBehavior: TopAppBarScrollBehavior,
    navigate: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        scrollBehavior = scrollBehavior,
        title = {
            Text(
                text = stringResource(R.string.profileapp),
                color = MaterialTheme.colorScheme.primary
            )
        },
        actions = {
            IconButton(onClick = { navigate() }) {
                Icon(imageVector = Icons.Default.Settings, contentDescription = "Setting")
            }
        },
        modifier = modifier
    )
}