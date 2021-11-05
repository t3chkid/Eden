package com.example.eden.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.eden.data.domain.PetInfo

/**
 * A custom styled material card that is used for displaying
 * a pet's name,type and breed using the data in the [PetInfo]
 * class.
 * @param onClick the action to be performed when the card is
 * clicked.
 * @param modifier the modifier that will be applied to the card.
 */
@ExperimentalMaterialApi
@Composable
fun PetCarouselCard(
    petInfo: PetInfo,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val scrim = Brush.verticalGradient(
        colors = listOf(Color.Transparent, Color.Black),
        startY = 0.0f,
        endY = 1000.0f
    )
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        onClick = onClick
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
//            Image(
//                modifier = Modifier.fillMaxSize(),
//                painter = painterResource(id = petInfo.imageResource),
//                contentDescription = "",
//                contentScale = ContentScale.Crop,
//            )
            Spacer(
                modifier = Modifier
                    .background(scrim)
                    .fillMaxSize()
            )
            Column(
                modifier = Modifier
                    .align(Alignment.BottomStart)
                    .padding(8.dp)
            ) {
                Text(
                    modifier = Modifier,
                    text = petInfo.name,
                    style = MaterialTheme.typography.h1,
                    color = MaterialTheme.colors.surface,
                    textAlign = TextAlign.Center
                )
                Text(
                    modifier = Modifier,
                    text = "${petInfo.type} | ${petInfo.breed}",
                    style = MaterialTheme.typography.caption,
                    color = MaterialTheme.colors.surface,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
