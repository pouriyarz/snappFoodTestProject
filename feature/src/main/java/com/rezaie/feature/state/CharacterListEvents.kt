package com.rezaie.feature.state


sealed class CharacterListEvents {

    data class GetCharacters(val query: String) : CharacterListEvents()
}
