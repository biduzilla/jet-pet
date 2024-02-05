package com.ricky.jetpet.apresentation.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ricky.jetpet.apresentation.detail.components.PetBasicInfo
import com.ricky.jetpet.components.OwnerCardInfo
import com.ricky.jetpet.domain.DummyPetDataSource
import com.ricky.jetpet.domain.model.Pet

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    state: DetailState,
    navController: NavController,
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
                            navController.popBackStack()
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
        LazyColumn(contentPadding = paddingValues) {
            item {
                Image(
                    painter = painterResource(id = state.pet.image),
                    contentDescription = state.pet.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(346.dp),
                    alignment = Alignment.CenterStart,
                    contentScale = ContentScale.Crop
                )
                Spacer(modifier = Modifier.height(16.dp))
                PetBasicInfo(
                    name = state.pet.name,
                    gender = state.pet.gender,
                    location = state.pet.location
                )
            }
            item {
                MyStoryItem(pet = state.pet)
            }
            item {
                PetInfo(pet = state.pet)
            }

            item {
                OwnerCardInfo(owner = state.pet.owner)
            }
            item {
                PetButton {

                }
            }
        }
    }
}

@Composable
fun PetButton(onClick: () -> Unit) {
    Spacer(modifier = Modifier.height(36.dp))
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        shape = RoundedCornerShape(16.dp)
    ) {
        Text(
            modifier = Modifier.padding(vertical = 8.dp),
            text = "Adopt Me",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Composable
fun MyStoryItem(pet: Pet, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Spacer(modifier = Modifier.height(24.dp))
        Title(title = "My Story")
        Text(
            text = pet.description,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            color = MaterialTheme.colorScheme.onSurface,
            style = MaterialTheme.typography.labelMedium,
            textAlign = TextAlign.Start
        )
    }
}

@Composable
fun Title(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp),
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.titleMedium,
        fontWeight = FontWeight.W700,
        textAlign = TextAlign.Start
    )
}

@Composable
fun PetInfo(pet: Pet, modifier: Modifier = Modifier) {
    Column(modifier = Modifier) {
        Spacer(modifier = Modifier.height(16.dp))
        Title(title = "Pet Info")
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            InfoCard(
                primaryText = pet.age,
                secondaryText = "Age",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.color,
                secondaryText = "Color",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )
            InfoCard(
                primaryText = pet.breed,
                secondaryText = "Breed",
                modifier = Modifier
                    .weight(1f)
                    .padding(4.dp)
            )

        }
    }
}

@Composable
fun InfoCard(
    modifier: Modifier = Modifier,
    primaryText: String,
    secondaryText: String
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium
    ) {
        Column(
            Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            CompositionLocalProvider(
                LocalContentColor provides MaterialTheme.colorScheme.onSurface.copy(
                    alpha = 0.38f
                )
            ) {
                Text(text = secondaryText)
            }
            Text(
                text = primaryText,
                style = MaterialTheme.typography.bodyLarge,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}

@Preview
@Composable
private fun DetailsScreenPreview() {
    val context = LocalContext.current
    DetailScreen(
        state = DetailState(),
        navController = NavController(context)
    )
}

@Preview(showBackground = true)
@Composable
private fun InfoCardPreview() {
    PetInfo(pet = DummyPetDataSource.dogList[0])
}