package com.ricky.jetpet.apresentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.jetpet.data.DummyPetDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    index: Int,
    onNavigate: () -> Unit
) {
    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Detail") },
            navigationIcon = {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null,
                    modifier = Modifier
                        .clickable {
                            onNavigate.invoke()
                        }
                        .padding(start = 8.dp, end = 24.dp),
                    tint = MaterialTheme.colorScheme.onSurface
                )
            },
            colors = TopAppBarDefaults.largeTopAppBarColors(
                containerColor = MaterialTheme.colorScheme.background,
                titleContentColor = MaterialTheme.colorScheme.onSurface
            )
        )
    }) { paddingValues ->
        val pet = DummyPetDataSource.dogList[index]
        LazyColumn(contentPadding = paddingValues) {
            item {
                Image(
                    painter = painterResource(id = pet.image),
                    contentDescription = pet.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop
                )
            }
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    DetailScreen(index = 0) {

    }
}