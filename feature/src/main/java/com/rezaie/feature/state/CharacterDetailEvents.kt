package com.rezaie.feature.state

import com.rezaie.feature.presentation.CharacterView


sealed class CharacterDetailEvents {

    data class GetCharacterDetail(val characterView: CharacterView) : CharacterDetailEvents()
    data object ClearError : CharacterDetailEvents()
}
