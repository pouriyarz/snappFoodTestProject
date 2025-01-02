package com.rezaie.feature

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import coil.ImageLoader
import com.rezaie.components.BaseScreen
import com.rezaie.components.LoadingShimmerMovieItem
import com.rezaie.core.constants.Queue
import com.rezaie.domain.domainCore.ProgressBarState
import com.rezaie.feature.ui.component.CharacterListItem
import com.rezaie.feature.ui.component.CharacterListToolbar


@ExperimentalFoundationApi
@ExperimentalComposeUiApi
@ExperimentalAnimationApi
@Composable
fun CharacterListScreen(
    viewModel: CharacterListViewModel = hiltViewModel(),
    imageLoader: ImageLoader
) {
    val characters = viewModel.characters.collectAsLazyPagingItems()

    BaseScreen(
        queue = Queue(mutableListOf()),
        progressBarState = ProgressBarState.Idle
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                CharacterListToolbar(onTextChange = { query ->
                    viewModel.updateQuery(query)
                })

                Box(modifier = Modifier.fillMaxSize()) {
                    when {
                        characters.loadState.refresh is LoadState.Loading ||
                                characters.loadState.prepend.endOfPaginationReached.not()  -> {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(20) {
                                    LoadingShimmerMovieItem(imageHeight = 130.dp)
                                }
                            }
                        }

                        // Display Characters List Once Loaded
                        characters.itemCount > 0 -> {
                            if (characters.loadState.prepend.endOfPaginationReached) {
                            LazyColumn(
                                modifier = Modifier.fillMaxSize()
                            ) {
                                items(characters.itemCount) { index ->
                                    if (characters[index] != null) {
                                        CharacterListItem(
                                            character = characters[index]!!,
                                            imageLoader = imageLoader
                                        )
                                    }
                                }
                            }
                        }
                            }

                        characters.loadState.refresh is LoadState.Error && characters.itemCount == 0 -> {
                            val e = characters.loadState.refresh as LoadState.Error
                            ErrorItem(
                                error = e.error,
                                onRetry = { characters.retry() }
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ErrorItem(
    error: Throwable,
    onRetry: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error: ${error.localizedMessage}")
        Button(onClick = onRetry) {
            Text(text = "Retry")
        }
    }
}

