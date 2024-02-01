package com.ricky.jetpet.apresentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.ricky.jetpet.components.PetInfoItem
import com.ricky.jetpet.data.DummyPetDataSource

@Composable
fun HomeScreen() {
    LazyColumn {
        items(DummyPetDataSource.dogList) {pet->
            PetInfoItem(pet = pet){

            }
        }
    }
}