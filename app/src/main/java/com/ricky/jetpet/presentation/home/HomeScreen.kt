package com.ricky.jetpet.presentation.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
    val scrollState = rememberLazyListState()
    val context = LocalContext.current

    if (state.error.isBlank()) {
        Toast.makeText(context, state.error, Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        topBar = {
            TopBar {
                onEvent(HomeEvent.OnSwitch)
            }
        }
    ) { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues,
            state = scrollState
        ) {
            items(state.pets) { pet ->
                PetInfoItem(pet = pet) {
                    navController.navigate(Screens.DetailScreen.route + "/${pet.id}")
                }
            }
            if (state.isLoading) {
                item {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentSize(align = Alignment.Center)
                    )
                }
            }
            item {
                AnimatedVisibility(visible = state.loadMoreButtonVisible) {
                    Row(horizontalArrangement = Arrangement.Center) {
                        TextButton(modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp),
                            onClick = {
                                onEvent(HomeEvent.LoadPets)

                            }) {
                            Text(text = "Load More Pets")
                        }
                    }
                }
            }
        }
    }
}