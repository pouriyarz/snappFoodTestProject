package com.rezaie.feature

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import com.rezaie.components.BaseScreen
import com.rezaie.components.LoadingShimmerCharacterItem
import com.rezaie.feature.presentation.CharacterView
import com.rezaie.feature.ui.component.CharacterListItem
import com.rezaie.feature.ui.component.CharacterListToolbar
import com.rezaie.components.R as componentsR


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    imageLoader: ImageLoader,
    onCharacterClick: (CharacterView) -> Unit
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    var searchQuery by rememberSaveable { mutableStateOf("") }

    BackHandler(enabled = searchQuery.isNotEmpty()) {
        if (searchQuery.isNotEmpty()) {
            searchQuery = ""
            viewModel.updateQuery("")
        }
    }

    BaseScreen {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CharacterListToolbar(onTextChange = { query ->
                    searchQuery = query
                    viewModel.updateQuery(query)
                }, query = searchQuery)

                Box(modifier = Modifier.fillMaxSize()) {
                    when {
                        characters.loadState.refresh is LoadState.Loading ||
                                (characters.loadState.prepend.endOfPaginationReached.not() && characters.loadState.refresh !is LoadState.Error) -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(20) {
                                    LoadingShimmerCharacterItem(imageHeight = 130.dp)
                                }
                            }
                        }

                        characters.loadState.refresh is LoadState.Error &&
                                characters.itemCount == 0 && characters.loadState.source.append.endOfPaginationReached -> {
                            ErrorItem(
                                onRetry = { characters.retry() }
                            )
                        }

                        characters.itemCount > 0 -> {
                            if (characters.loadState.prepend.endOfPaginationReached || characters.loadState.refresh is LoadState.Error) {
                                LazyColumn(
                                    modifier = Modifier.fillMaxSize(),
                                    contentPadding = PaddingValues(bottom = 70.dp)
                                ) {
                                    items(characters.itemCount) { index ->
                                        if (characters[index] != null) {
                                            CharacterListItem(
                                                character = characters[index]!!,
                                                imageLoader = imageLoader,
                                                onClick = onCharacterClick
                                            )
                                        }
                                    }

                                    if (characters.loadState.append is LoadState.Loading) {
                                        item {
                                            Box(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(16.dp),
                                                contentAlignment = Alignment.Center
                                            ) {
                                                CircularProgressIndicator()
                                            }
                                        }
                                    }
                                }
                            }
                        }

                        characters.itemCount == 0 && searchQuery.isNotEmpty() && characters.loadState.prepend.endOfPaginationReached -> {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .padding(16.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(componentsR.string.no_character_found),
                                    style = MaterialTheme.typography.h6,
                                    modifier = Modifier.padding(16.dp),
                                    color = MaterialTheme.colors.onBackground
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ErrorItem(
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = stringResource(componentsR.string.error))
        Button(onClick = onRetry) {
            Text(text = stringResource(componentsR.string.retry))
        }
    }
}

