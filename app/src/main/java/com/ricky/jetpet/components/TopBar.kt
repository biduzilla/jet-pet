package com.ricky.jetpet.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
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

@Composable
fun TopBar(onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
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
        PetSwitch {
            onClick.invoke()
        }
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(
//                    top = 24.dp,
//                    end = 36.dp
//                )
//        ) {
//            PetSwitch {
//                onClick.invoke()
//            }
//        }
    }
}

@Composable
fun PetSwitch(onClick: () -> Unit) {
    val icon =
        if (isSystemInDarkTheme()) painterResource(id = R.drawable.ic_switch_on) else painterResource(
            R.drawable.ic_switch_off
        )

    Icon(
        painter = icon,
        contentDescription = null,
        modifier = Modifier
            .size(24.dp)
            .clickable { onClick.invoke() },
        tint = MaterialTheme.colorScheme.onSurface
    )
}

@Preview(showSystemUi = true)
@Composable
private fun TopBarPreview() {
    TopBar {

    }
}