package com.example.myprofile.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.myprofile.data.model.Profile

@Composable
fun ProfileListItem(
    profile: Profile,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
            .padding(top = 4.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            Text(text = profile.name, modifier = Modifier.padding(start = 4.dp))
            Text(text = profile.age.toString(), modifier = Modifier.padding(start = 4.dp))
            Text(
                text = profile.address,
                modifier = Modifier
                    .padding(start = 4.dp)
                    .padding(bottom = 4.dp)
            )
        }
    }

}