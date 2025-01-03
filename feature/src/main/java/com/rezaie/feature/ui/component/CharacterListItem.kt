package com.rezaie.feature.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rezaie.components.extenssions.getIdFromUrl
import com.rezaie.components.extenssions.orZero
import com.rezaie.feature.BuildConfig
import com.rezaie.feature.R
import com.rezaie.feature.presentation.CharacterView
import com.rezaie.components.R as componentsR


@Composable
fun CharacterListItem(
    character: CharacterView,
    imageLoader: ImageLoader,
    onClick: (CharacterView) -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(top = 12.dp, start = 16.dp, end = 16.dp)
            .clip(RoundedCornerShape(16.dp))
            .clickable { onClick(character) },
        elevation = 4.dp,
        color = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 12.dp, bottom = 12.dp, start = 16.dp, end = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Image
            val imageId = character.url?.getIdFromUrl()
            AsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("${BuildConfig.BASE_URL_IMAGE}$imageId.jpg")
                    .error(R.drawable.ic_star_wars)
                    .placeholder(if (isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
                    .build(),
                contentDescription = character.name,
                modifier = Modifier
                    .height(110.dp)
                    .width(80.dp)
                    .clip(RoundedCornerShape(12.dp)),
                contentScale = ContentScale.FillBounds,
                imageLoader = imageLoader
            )

            Spacer(modifier = Modifier.width(15.dp))

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
                    color = MaterialTheme.colors.onBackground
                )

                // Birth Year
                Text(
                    modifier = Modifier.padding(top = 10.dp),
                    text = "Born: ${character.birthYear}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
                )

                // Height
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "${character.height}${stringResource(componentsR.string.centi_meteres)} ${stringResource(
                        componentsR.string.height)}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
                )

                // Number of Films
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "${character.films?.size.orZero()} ${stringResource(componentsR.string.films)}",
                    style = MaterialTheme.typography.body2,
                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f)
                )
            }
        }
    }
}




