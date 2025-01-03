package com.rezaie.feature

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.rezaie.components.BaseScreen
import com.rezaie.components.extenssions.getIdFromUrl
import com.rezaie.components.extenssions.replaceNewlinesWithSpace
import com.rezaie.feature.presentation.CharacterView
import com.rezaie.components.R as componentsR

@Composable
fun CharacterDetailScreen(
    characterView: CharacterView,
    viewModel: CharacterDetailViewModel = hiltViewModel(),
    imageLoader: ImageLoader
) {
    val characterDetail = viewModel.characterDetail.collectAsState()
    val hasError = viewModel.hasError.value
    val context = LocalContext.current
    val errorText = stringResource(componentsR.string.error)

    LaunchedEffect(hasError) {
        if (hasError && characterDetail.value?.films?.isEmpty() == true) {
            Toast.makeText(context, errorText, Toast.LENGTH_SHORT).show()
            viewModel.clearError()
        }
    }

    LaunchedEffect(characterView) {
        viewModel.getCharacterDetail(characterView)
    }
    BaseScreen {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            elevation = 0.dp,
        ) {

            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .verticalScroll(rememberScrollState())
                    .fillMaxSize(),
            ) {

                // Image
                val imageId = characterView.url?.getIdFromUrl()
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data("${BuildConfig.BASE_URL_IMAGE}$imageId.jpg")
                        .error(R.drawable.ic_star_wars)
                        .placeholder(if (isSystemInDarkTheme()) R.drawable.black_background else R.drawable.white_background)
                        .build(),
                    contentDescription = characterView.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 50.dp, end = 50.dp)
                        .clip(RoundedCornerShape(20.dp))
                        .height(350.dp),
                    contentScale = ContentScale.FillBounds,
                    imageLoader = imageLoader
                )


                Text(
                    modifier = Modifier
                        .padding(top = 16.dp)
                        .align(Alignment.Start),
                    text = characterDetail.value?.name.toString(),
                    style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold),
                )


                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                        .horizontalScroll(rememberScrollState())
                ) {


                    Card(
                        elevation = 0.dp,
                        shape = RoundedCornerShape(25.dp),
                        backgroundColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray,
                    ) {
                        Row(
                            modifier = Modifier.padding(all = 12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = "Born: ${characterDetail.value?.birthYear.toString()}",
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }

                    Spacer(modifier = Modifier.width(8.dp))

                    Card(
                        elevation = 0.dp,
                        shape = RoundedCornerShape(25.dp),
                        backgroundColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray,

                        ) {
                        Row(
                            modifier = Modifier.padding(all = 12.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {

                            Text(
                                modifier = Modifier.padding(start = 4.dp),
                                text = "${characterView.height}${stringResource(componentsR.string.centi_meteres)} ${
                                    stringResource(
                                        componentsR.string.height
                                    )
                                }",
                                style = MaterialTheme.typography.subtitle1,
                                color = MaterialTheme.colors.onBackground
                            )
                        }
                    }

                    characterDetail.value?.homeWorld?.let {
                        Spacer(modifier = Modifier.width(8.dp))

                        Card(
                            elevation = 0.dp,
                            shape = RoundedCornerShape(25.dp),
                            backgroundColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray,
                        ) {
                            Row(
                                modifier = Modifier.padding(all = 12.dp),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {

                                Text(
                                    modifier = Modifier.padding(start = 4.dp),
                                    text = "${stringResource(componentsR.string.population)}: ${it.population}",
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.onBackground
                                )
                            }
                        }
                    }

                    // Species Section
                    if (characterDetail.value?.species?.isNotEmpty() == true) {

                        Spacer(modifier = Modifier.width(8.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                        ) {
                            characterDetail.value?.species?.forEach { species ->
                                Card(
                                    elevation = 0.dp,
                                    shape = RoundedCornerShape(25.dp),
                                    backgroundColor = if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray,

                                    ) {
                                    Row(
                                        modifier = Modifier.padding(all = 12.dp),
                                        horizontalArrangement = Arrangement.Center,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {

                                        Text(
                                            modifier = Modifier.padding(start = 4.dp),
                                            text = "${stringResource(componentsR.string.language)}: ${
                                                characterDetail.value?.species?.get(
                                                    0
                                                )?.language.toString()
                                            }",
                                            style = MaterialTheme.typography.subtitle1,
                                            color = MaterialTheme.colors.onBackground
                                        )
                                    }
                                }
                            }
                        }
                    }
                }

                // Films Section
                if (characterDetail.value?.films?.isNotEmpty() == true) {
                    Spacer(modifier = Modifier.height(16.dp))

                    Text(
                        text = stringResource(componentsR.string.films),
                        style = MaterialTheme.typography.h4,
                        modifier = Modifier.padding(bottom = 8.dp, top = 8.dp)
                    )

                    val expandedFilmIndices =
                        remember { mutableStateListOf<Int>() }

                    characterDetail.value?.films?.forEachIndexed { index, film ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(bottom = 8.dp)
                                .clickable {
                                    if (expandedFilmIndices.contains(index)) {
                                        expandedFilmIndices.remove(index)
                                    } else {
                                        expandedFilmIndices.add(index)
                                    }
                                }
                                .background(
                                    if (MaterialTheme.colors.isLight) Color.LightGray else Color.DarkGray,
                                    RoundedCornerShape(12.dp)
                                )
                                .padding(16.dp)
                        ) {
                            Row(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween, // Align elements at both ends
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = film.name,
                                    style = MaterialTheme.typography.subtitle1,
                                    color = MaterialTheme.colors.onBackground,
                                    modifier = Modifier.padding(
                                        bottom = if (expandedFilmIndices.contains(index)) 8.dp else 0.dp
                                    )
                                )

                                Icon(
                                    imageVector = if (expandedFilmIndices.contains(index)) {
                                        Icons.Default.ArrowDropUp
                                    } else {
                                        Icons.Default.ArrowDropDown
                                    },
                                    contentDescription = "Expand/Collapse",
                                    tint = MaterialTheme.colors.onBackground,
                                    modifier = Modifier
                                        .padding(start = 8.dp)
                                        .clickable {
                                            if (expandedFilmIndices.contains(index)) {
                                                expandedFilmIndices.remove(index)
                                            } else {
                                                expandedFilmIndices.add(index)
                                            }
                                        }
                                )
                            }

                            if (expandedFilmIndices.contains(index)) {
                                Text(
                                    text = film.openingCrawl.replaceNewlinesWithSpace(),
                                    style = MaterialTheme.typography.subtitle2,
                                    color = MaterialTheme.colors.onBackground.copy(alpha = 0.8f),
                                    modifier = Modifier.padding(top = 8.dp)
                                )
                            }
                        }
                    }

                }
            }
        }
    }
}

