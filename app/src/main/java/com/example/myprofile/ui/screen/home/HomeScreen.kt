@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.myprofile.ui.screen.home

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.data.Profile
import com.example.myprofile.ui.component.SmallFAB

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()

    HomeContent(
        profileList = homeUiState.profile,
        navigateToAdd = navigateToAdd,
        modifier = modifier.fillMaxSize()
    )

}

@Composable
fun HomeContent(
    profileList: List<Profile>,
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit
) {
    Scaffold(
        floatingActionButton = {
            SmallFAB(navigateToAdd = { navigateToAdd() })
        }
    ) {
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (profileList.isEmpty()) {
                Text(text = "No data", modifier = modifier)
            } else {
                ProfileList(profileList = profileList, modifier = modifier)
            }
        }
    }
}

@Composable
fun ProfileList(
    profileList: List<Profile>,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        items(profileList) { data ->
            ProfileItem(profile = data)
        }
    }
}

@Composable
fun ProfileItem(
    profile: Profile
) {
    Text(text = profile.name)
}