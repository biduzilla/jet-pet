package com.ricky.jetpet.apresentation.home

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.jetpet.components.PetInfoItem
import com.ricky.jetpet.components.TopBar
import com.ricky.jetpet.navigation.Screens
import com.ricky.jetpet.utils.ResourceHolder

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    state: HomeState,
    navController: NavController,
    onEvent: (HomeEvent) -> Unit,
) {
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    if (state.isError) {
        Toast.makeText(context, state.error, Toast.LENGTH_LONG).show()
    }
    Scaffold(
        topBar = {
            TopBar {
                onEvent(HomeEvent.OnSwitch)
            }
        }
    ) { paddingValues ->
        LazyColumn(contentPadding = paddingValues) {
            when (state.animals) {
                is ResourceHolder.Loading -> {
                    item {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .fillMaxSize()
                                .wrapContentSize(align = Alignment.Center)
                        )
                    }
                }

                is ResourceHolder.Success -> {
                    val petList = state.animals.data ?: emptyList()
                    itemsIndexed(petList) { index, pet ->
                        PetInfoItem(pet = pet) {
                            navController.navigate(Screens.DetailScreen.route + "/${pet.id}")
                        }
                        LaunchedEffect(key1 = scrollState) {
                            if (
                                index >= petList.lastIndex &&
                                !state.isFetchingPet &&
                                state.startInfiniteScrolling
                            ) {
                                onEvent(HomeEvent.OnLoadNextPage)
                            }
                        }
                    }
                    if (state.isFetchingPet) {
                        item {
                            CircularProgressIndicator()
                        }
                    }
                    item {
                        AnimatedVisibility(visible = state.loadMoreButtonVisible) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(8.dp)
                            ) {
                                TextButton(onClick = {
                                    onEvent(
                                        HomeEvent.OnLoadNextPage
                                    )
                                    onEvent(
                                        HomeEvent.OnInfiniteScrollingChange(true)
                                    )
                                }) {
                                    Text(text = "Load More Pets")
                                }
                            }
                        }
                    }
                }

                else -> {
                    state.animals.throwable?.printStackTrace()
                    Toast.makeText(
                        context,
                        state.animals.throwable?.message,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }
}