package com.ricky.jetpet.apresentation.home

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ricky.jetpet.components.PetInfoItem
import com.ricky.jetpet.components.TopBar
import com.ricky.jetpet.navigation.Screens

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavController,
    onEvent: (HomeEvent) -> Unit,
) {
    val petList = DummyPetDataSource.dogList
    Scaffold(
        topBar = {
            TopBar {
                onEvent(HomeEvent.OnSwitch)
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            items(state.pets) { pet ->
                PetInfoItem(pet = pet) {
                    navController.navigate(Screens.DetailScreen.route + "/${pet.id}")
                }
            }
        }
    }
}