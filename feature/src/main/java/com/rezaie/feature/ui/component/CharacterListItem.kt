package com.rezaie.feature.ui.component

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Group
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.request.ImageRequest
import com.rezaie.feature.R
import com.rezaie.feature.presentation.CharacterView


@Composable
fun CharacterListItem(
    character: CharacterView,
    imageLoader: ImageLoader
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp) // Outer padding
            .clip(RoundedCornerShape(16.dp)), // Rounded corners
        elevation = 4.dp, // Shadow to make it look elevated
        color = MaterialTheme.colors.surface
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp), // Padding inside the row
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            val imageId = Regex(".*/(\\d+)/").find(character.url.toString())?.groupValues?.get(1)
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://starwars-visualguide.com/assets/img/characters/$imageId.jpg")
                    .error(R.drawable.ic_baseline_star_24) // Fallback image if the character image is not found
                    .placeholder(R.drawable.black_background) // Placeholder for loading state
                    .build(),
                contentDescription = character.name,
                modifier = Modifier
                    .size(100.dp) // Set the size of the image
                    .clip(RoundedCornerShape(20.dp)), // Circular image
                contentScale = ContentScale.Crop,
                imageLoader = imageLoader
            )

            Spacer(modifier = Modifier.width(15.dp)) // Space between image and text

            // Column to show Name, Birth Year, Height, and Number of Films
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(vertical = 10.dp),
                verticalArrangement = Arrangement.Center
            ) {
                // Name
                Text(
                    text = character.name,
                    style = MaterialTheme.typography.h6.copy(fontWeight = FontWeight.Bold),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color.Cyan
                )

                // Birth Year
                Text(
                    text = "Born: ${character.birthYear}",
                    style = MaterialTheme.typography.body2.copy(fontStyle = FontStyle.Italic),
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.7f)
                )

                // Height
                Text(
                    text = "Height: ${character.height}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )

                // Number of Films
                Text(
                    text = "Films: ${character.films?.size ?: 0}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onSurface.copy(alpha = 0.8f)
                )
            }
        }
    }
}




