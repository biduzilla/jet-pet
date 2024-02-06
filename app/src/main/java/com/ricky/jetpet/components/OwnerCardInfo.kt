package com.ricky.jetpet.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ricky.jetpet.R
import com.ricky.jetpet.domain.model.Owner

@Composable
fun OwnerCardInfo(
    modifier: Modifier = Modifier,
    owner: Owner
) {
    Spacer(modifier = Modifier.height(16.dp))
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painter = painterResource(id = owner.image),
                contentDescription = owner.name,
                modifier = Modifier
                    .size(60.dp)
                    .clip(RoundedCornerShape(16.dp)),
                alignment = Alignment.Center,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(
                    text = owner.name,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = owner.basicInfo,
                    color = MaterialTheme.colorScheme.onSurface,
                    style = MaterialTheme.typography.bodySmall
                )
            }
        }
        Surface(modifier = Modifier
            .clickable { }
            .size(40.dp),
            shape = CircleShape,
            color = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary) {
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier.size(20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_messenger),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun OwnerCardInfoPreview() {
    OwnerCardInfo(owner = DummyPetDataSource.dogList[0].owner)
}