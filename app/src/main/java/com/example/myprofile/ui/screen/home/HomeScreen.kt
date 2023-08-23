@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterial3Api::class)

package com.example.myprofile.ui.screen.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.myprofile.data.model.Profile
import com.example.myprofile.ui.component.MyFAB
import com.example.myprofile.ui.component.MyTopBar
import com.example.myprofile.ui.component.ProfileListItem

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    navigateToSetting: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior,
    homeViewModel: HomeViewModel = viewModel(factory = HomeViewModel.Factory)
) {
    val homeUiState by homeViewModel.homeUiState.collectAsState()

    HomeContent(
        profileList = homeUiState.profile,
        navigateToAdd = navigateToAdd,
        navigateToDetail = navigateToDetail,
        navigateToSetting = navigateToSetting,
        scrollBehavior = scrollBehavior,
        modifier = modifier.fillMaxSize()
    )

}

@Composable
fun HomeContent(
    profileList: List<Profile>,
    modifier: Modifier = Modifier,
    navigateToAdd: () -> Unit,
    navigateToDetail: (Int) -> Unit,
    navigateToSetting: () -> Unit,
    scrollBehavior: TopAppBarScrollBehavior

) {
    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = { MyTopBar(scrollBehavior = scrollBehavior, navigate = { navigateToSetting() }) },
        floatingActionButton = {
            MyFAB(navigate = { navigateToAdd() }, imageVector = Icons.Default.Add)
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
                    ProfileList(
                        profileList = profileList,
                        modifier = modifier,
                        navigateToDetail
                    )
                }
        }
    }
}

@Composable
fun ProfileList(
    profileList: List<Profile>,
    modifier: Modifier = Modifier,
    navigateToDetail: (Int) -> Unit

) {
    LazyColumn(modifier = modifier) {
        items(profileList) { data ->
            ProfileListItem(
                profile = data,
                modifier = modifier
                    .clickable { navigateToDetail(data.id) }
            )
        }
    }
}

