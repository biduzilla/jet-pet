package com.ricky.jetpet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.jetpet.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(onClick: () -> Unit) {
    CenterAlignedTopAppBar(actions = {
        PetSwitch {
            onClick()
        }
    }, title = {
        Column(
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = "Hey Lucky",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.titleSmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = "Find a new friend near to you to adopt",
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    })
}

@Composable
fun PetSwitch(onClick: () -> Unit) {
    val icon =
        if (isSystemInDarkTheme()) painterResource(id = R.drawable.ic_switch_on) else painterResource(
            R.drawable.ic_switch_off
        )
    IconButton(onClick = onClick) {
        Icon(
            painter = icon,
            contentDescription = null,
            modifier = Modifier
                .size(24.dp)
                .clickable { onClick.invoke() },
            tint = MaterialTheme.colorScheme.onSurface
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun TopBarPreview() {
    TopBar {

    }
}