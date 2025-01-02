package com.rezaie.snappfoodtestproject.ui.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class Screen(val route: String, val arguments: List<NamedNavArgument>){

    data object CharacterList: Screen(
        route = "characterList",
        arguments = emptyList()
    )
}