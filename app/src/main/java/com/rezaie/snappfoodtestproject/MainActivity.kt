package com.rezaie.snappfoodtestproject

import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import com.rezaie.feature.CharacterDetailScreen
import com.rezaie.feature.CharacterDetailViewModel
import com.rezaie.feature.CharacterListScreen
import com.rezaie.feature.CharacterListViewModel
import com.rezaie.feature.presentation.CharacterView
import com.rezaie.snappfoodtestproject.ui.navigation.Screen
import com.rezaie.snappfoodtestproject.ui.theme.BaseTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import javax.inject.Inject


@AndroidEntryPoint
@ExperimentalComposeUiApi
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var imageLoader: ImageLoader

    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BaseTheme {
                BoxWithConstraints {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = Screen.CharacterList.route,
                        builder = {
                            addCharacterList(
                                navController = navController,
                                imageLoader = imageLoader
                            )
                            addCharacterDetail(
                                imageLoader = imageLoader
                            )
                        }
                    )
                }
            }
        }
    }

    @OptIn(ExperimentalAnimationApi::class)
    @ExperimentalFoundationApi
    fun NavGraphBuilder.addCharacterList(
        navController: NavController,
        imageLoader: ImageLoader
    ) {
        composable(
            route = Screen.CharacterList.route
        ) {
            val viewModel: CharacterListViewModel = hiltViewModel()
            CharacterListScreen(
                viewModel = viewModel,
                imageLoader = imageLoader,
                onCharacterClick = { selectedCharacter ->
                    val characterView = Uri.encode(Json.encodeToString(selectedCharacter))
                    navController.navigate(Screen.CharacterDetail.route + "/$characterView")
                }
            )
        }
    }

    private fun NavGraphBuilder.addCharacterDetail(
        imageLoader: ImageLoader
    ) {
        composable(
            route = Screen.CharacterDetail.route + "/{characterView}",
            arguments = Screen.CharacterDetail.arguments
        ) { backStackEntry ->
            val characterJson = backStackEntry.arguments?.getString("characterView")
            val characterView = characterJson?.let { Json.decodeFromString<CharacterView>(it) }

            val viewModel: CharacterDetailViewModel = hiltViewModel()
            if (characterView != null) {
                CharacterDetailScreen(
                    characterView = characterView,
                    viewModel = viewModel,
                    imageLoader = imageLoader
                )
            }
        }
    }
}