package com.example.skilltest.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.example.skilltest.data.model.Card as CardModel

@Composable
fun CardItem(card: CardModel) {
    Card(
        modifier = Modifier
            .size(80.dp)
            .padding(4.dp)
    ) {
        Image(
            painter = rememberAsyncImagePainter(card.image),
            contentDescription = card.code,
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.Crop
        )
    }
}