package com.rezaie.feature.state


sealed class CharacterListEvents {

    data class UpdateQuery(val query: String) : CharacterListEvents()
}
