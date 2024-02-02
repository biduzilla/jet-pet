package com.ricky.jetpet.apresentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.ricky.jetpet.components.PetInfoItem
import com.ricky.jetpet.components.TopBar
import com.ricky.jetpet.data.DummyPetDataSource

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onSwitchClick: () -> Unit,
    onPetClick: (Int) -> Unit
) {
    val petList = DummyPetDataSource.dogList
    Scaffold(
        topBar = {
            TopBar {
                onSwitchClick()
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            itemsIndexed(petList) { i, pet ->
                PetInfoItem(pet = pet) {
                    onPetClick(i)
                }
            }
        }
    }

}